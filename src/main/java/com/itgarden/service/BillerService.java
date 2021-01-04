package com.itgarden.service;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.TaxCalculation;
import com.itgarden.common.TaxCalculationInput;
import com.itgarden.common.TaxCalculationResponse;
import com.itgarden.common.staticdata.BillStatus;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.staticdata.StockStatus;
import com.itgarden.common.staticdata.UserType;
import com.itgarden.dto.*;
import com.itgarden.entity.*;
import com.itgarden.mapper.BillerMapper;
import com.itgarden.mapper.CustomerMapper;
import com.itgarden.mapper.PaymentMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillerService extends BaseService<PaymentRequest> {


    private final CustomerRepository customerRepository;

    private final ProductItemRepository productItemRepository;

    private final TaxCalculation taxCalculation;

    private final BillerRepository billerRepository;

    private final PaymentRepository paymentRepository;

    private final CodeGenerator codeGenerator;

    private final RegistrationService registrationService;

    private final ProductRepository productRepository;

    @Autowired
    public BillerService(CustomerRepository customerRepository, ProductItemRepository productItemRepository,
                         TaxCalculation taxCalculation,
                         BillerRepository billerRepository,
                         PaymentRepository paymentRepository,
                         CodeGenerator codeGenerator,
                         RegistrationService registrationService, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productItemRepository = productItemRepository;
        this.taxCalculation = taxCalculation;
        this.billerRepository = billerRepository;
        this.paymentRepository = paymentRepository;
        this.codeGenerator = codeGenerator;
        this.registrationService = registrationService;
        this.productRepository = productRepository;
    }

    private Biller sellProduct(Customer customer) {
        Biller biller = new Biller();
        biller.setCustomer(customer);
        biller.setQuantity(0);
        biller.setGrandTotal(0);
        biller.setBillNo(codeGenerator.newCode(CodeType.BILL_NO));
        biller.setBillStatus(BillStatus.SUCCESS.name());
        Biller newBiller = billerRepository.save(biller);
        return newBiller;
    }


    //    private double unitPrice;
//    private float taxPercentage;
//    private int quantity;
    private TaxCalculationResponse calculateTax(double unitPrice, float taxPercentage, int quantity) {
        TaxCalculationInput taxCalculationInput = new TaxCalculationInput(unitPrice, taxPercentage, quantity);
        taxCalculationInput.setUnitPrice(unitPrice);
        taxCalculationInput.setQuantity(quantity);
        taxCalculationInput.setTaxPercentage(taxPercentage);
        return taxCalculation.calculateTax(taxCalculationInput);
    }

    @Transactional
    public Biller updateBiller(Biller biller) {
        List<Payment> payments = paymentRepository.findPaymentByBiller(biller);
        for (Payment payment : payments) {
            payment.setPaymentStatus(BillStatus.REFUND.name());
            paymentRepository.save(payment);
            ProductItem productItem = payment.getProductItem();
            productItem.setStockStatus(StockStatus.IN_STOCK);
            productItemRepository.save(productItem);
            Product product = productItem.getProduct();
            product.setStockCount(product.getStockCount() + 1);
            productRepository.save(product);
        }
        billerRepository.save(biller);
        return billerRepository.save(biller);
    }

    @Transactional
    public ResponseMessage save(PaymentRequest paymentRequest) {
        ResponseMessage responseMessage = null;
        CustomerInfo customerInfo = null;
        try {
            List<String> productItemCodes = paymentRequest.getProductItemCode();
            String customerMobileNo = paymentRequest.getCustomerMobileNo();
            Customer customer = customerRepository.findCustomerByMobileNo(customerMobileNo);
            if (customer == null) {
                customer = new Customer();
                User user = new User();
                user.setMobileNo(customerMobileNo);
                user.setPassword(customerMobileNo);
                user.setUserType(UserType.CUSTOMER.name());
                customer.setUser(user);
                customerInfo = CustomerMapper.INSTANCE.customerToCustomerInfo(customer);
                customerInfo.setType(UserType.CUSTOMER.name());
                BaseInfo baseInfo = registrationService.doRegistration(customerInfo).getResponseClassType();
                customerInfo = (CustomerInfo) baseInfo;
            }
            List<ProductItem> productItems = productItemRepository.findProductItemByProductItemCodeIn(productItemCodes);
            customer = customerRepository.findCustomerByMobileNo(customerMobileNo);
            Biller biller = sellProduct(customer);
            double grandTotal = 0;
            double totalTaxAmount = 0;
            for (ProductItem productItem : productItems) {
                Payment payment = new Payment();
                payment.setProductItem(productItem);
                payment.setProductId(productItem.getProduct().getId());
                payment.setPaymentStatus(BillStatus.SUCCESS.name());
                payment.setBiller(biller);
                payment.setTax(productItem.getProduct().getTax());
                TaxCalculationResponse taxCalculationResponse =
                        calculateTax(productItem.getProduct().getPrice(), productItem.getProduct().getTax().getTaxPercentage(), 1);
                payment.setPrice(taxCalculationResponse.getTotalAmount()); // quantity * unit price
                payment.setTaxAmount(taxCalculationResponse.getTaxAmount());
                payment.setTotalPrice(taxCalculationResponse.getTotalAmount() + taxCalculationResponse.getTaxAmount()); // total amount + tax amount
                grandTotal = grandTotal + payment.getPrice();
                totalTaxAmount = totalTaxAmount + payment.getTaxAmount();
                paymentRepository.save(payment);
                productItem.setStockStatus(StockStatus.SOLD);
                productItemRepository.save(productItem);
                updateStock(productItem);
            }
            biller.setGrandTotal(grandTotal);
            biller.setQuantity(productItems.size());
            biller.setTotalTaxAmount(totalTaxAmount);
            Biller newBiller = billerRepository.save(biller);

            BillerInfo billerInfo = BillerMapper.INSTANCE.billerToBillerInfo(newBiller);
            responseMessage = ResponseMessage.withResponseData(billerInfo, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

    private void updateStock(ProductItem productItem) {
        Product product = productItem.getProduct();
        product.setStockCount(product.getStockCount() - 1);
        productRepository.save(product);
    }

    public List<PaymentInfo> cancel(PaymentRequest paymentRequest) {
        List<PaymentInfo> paymentInfos = new ArrayList<>();
        List<String> productItemCodes = paymentRequest.getProductItemCode();
        String customerMobileNo = paymentRequest.getCustomerMobileNo();
        List<ProductItem> productItems = productItemRepository.findProductItemByProductItemCodeIn(productItemCodes);
        for (ProductItem productItem : productItems) {
            Payment payment = paymentRepository.findPaymentByProductItem(productItem);
            payment.setDeleted(true);
            Payment newPayment = paymentRepository.save(payment);
            Biller biller = payment.getBiller();
            biller.setTotalTaxAmount(biller.getTotalTaxAmount() - payment.getTaxAmount());
            biller.setGrandTotal(biller.getGrandTotal() - payment.getPrice());
            billerRepository.save(biller);
            PaymentInfo paymentInfo = PaymentMapper.INSTANCE.paymentToPaymentInfo(newPayment);
            paymentInfos.add(paymentInfo);
            productItem.setStockStatus(StockStatus.IN_STOCK);
            productItemRepository.save(productItem);
        }
        return paymentInfos;
    }

    @Override
    public ResponseMessage findResourceById(Long id) throws Exception {
        return null;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        List<Biller> billers = billerRepository.findAll();
        List<BillerInfo> billerInfos = new ArrayList<>();
        for (Biller biller : billers) {
            BillerInfo billerInfo = BillerMapper.INSTANCE.billerToBillerInfo(biller);
            billerInfos.add(billerInfo);
        }
        ResponseMessage responseMessage = ResponseMessage.withResponseData(billerInfos, "", "");
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceByCode(String code) throws Exception {
        Biller biller = billerRepository.findBillerByBillNo(code);
        List<Payment> payments = paymentRepository.findPaymentByBiller(biller);
        List<PaymentInfo> paymentInfos = new ArrayList<>();
        for (Payment payment : payments) {
            PaymentInfo paymentInfo = PaymentMapper.INSTANCE.paymentToPaymentInfo(payment);
            paymentInfos.add(paymentInfo);
        }
        BillerInfo billerInfo = BillerMapper.INSTANCE.billerToBillerInfo(biller);
        billerInfo.setPaymentInfos(paymentInfos);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(billerInfo, "", "");
        return responseMessage;
    }

    public Biller findBillByBillNo(String code) throws Exception {
        Biller biller = billerRepository.findBillerByBillNo(code);
        return biller;
    }

    public ResponseMessage findPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        PaymentInfo paymentInfo = PaymentMapper.INSTANCE.paymentToPaymentInfo(payment);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(paymentInfo, "", "");
        return responseMessage;
    }

    public ResponseMessage cancelPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        payment.setPaymentStatus(BillStatus.REFUND.name());
        paymentRepository.save(payment);
        Biller biller = payment.getBiller();
        biller.setBillStatus(BillStatus.PARTIAL_REFUND.name());
        billerRepository.save(biller);
        ProductItem productItem = payment.getProductItem();
        productItem.setStockStatus(StockStatus.IN_STOCK);
        productItemRepository.save(productItem);
        Product product = productItem.getProduct();
        product.setStockCount(product.getStockCount() + 1);
        productRepository.save(product);
        PaymentInfo paymentInfo = PaymentMapper.INSTANCE.paymentToPaymentInfo(payment);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(paymentInfo, "", "");
        return responseMessage;
    }
}
