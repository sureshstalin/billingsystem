package com.itgarden.service;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.TaxCalculation;
import com.itgarden.common.TaxCalculationInput;
import com.itgarden.common.TaxCalculationResponse;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.staticdata.StockStatus;
import com.itgarden.common.staticdata.UserType;
import com.itgarden.dto.*;
import com.itgarden.entity.*;
import com.itgarden.mapper.BillerMapper;
import com.itgarden.mapper.CustomerMapper;
import com.itgarden.mapper.PaymentMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.BillerRepository;
import com.itgarden.repository.CustomerRepository;
import com.itgarden.repository.PaymentRepository;
import com.itgarden.repository.ProductItemRepository;
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

    @Autowired
    public BillerService(CustomerRepository customerRepository, ProductItemRepository productItemRepository,
                         TaxCalculation taxCalculation,
                         BillerRepository billerRepository,
                         PaymentRepository paymentRepository,
                         CodeGenerator codeGenerator,
                         RegistrationService registrationService) {
        this.customerRepository = customerRepository;
        this.productItemRepository = productItemRepository;
        this.taxCalculation = taxCalculation;
        this.billerRepository = billerRepository;
        this.paymentRepository = paymentRepository;
        this.codeGenerator = codeGenerator;
        this.registrationService = registrationService;
    }

    private Biller sellProduct(Customer customer, int quantity) {
        Biller biller = new Biller();
        biller.setCustomer(customer);
        biller.setQuantity(quantity);
        biller.setGrandTotal(0);
        biller.setBillNo(codeGenerator.newCode(CodeType.BILL_NO));
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
    public ResponseMessage save(PaymentRequest paymentRequest) {
        ResponseMessage responseMessage = null;
        CustomerInfo customerInfo = null;
        try {
            List<String> productItemCodes = paymentRequest.getProductItemCode();
            int quantity = paymentRequest.getQuantity();
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
            Biller biller = sellProduct(customer, quantity);
            double grandTotal = 0;
            double totalTaxAmount = 0;
            for (ProductItem productItem : productItems) {
                Payment payment = new Payment();
                payment.setProductItem(productItem);
                payment.setProductId(productItem.getProduct().getId());
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
            }
            biller.setGrandTotal(grandTotal);
            biller.setQuantity(productItems.size());
            biller.setTotalTaxAmount(totalTaxAmount);
            Biller newBiller = billerRepository.save(biller);

            BillerInfo billerInfo = BillerMapper.INSTANCE.billerToBillerInfo(newBiller);
            responseMessage = ResponseMessage.withResponseData(billerInfo, "", "");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

    public List<PaymentInfo> cancel(PaymentRequest paymentRequest) {
        List<PaymentInfo> paymentInfos = new ArrayList<>();
        List<String> productItemCodes = paymentRequest.getProductItemCode();
        String customerMobileNo = paymentRequest.getCustomerMobileNo();
        List<ProductItem> productItems = productItemRepository.findProductItemByProductItemCodeIn(productItemCodes);
        for (ProductItem productItem : productItems) {
            Payment payment = paymentRepository.findPaymentByProductItemAndDeletedFalse(productItem);
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
        return null;
    }

    @Override
    public ResponseMessage findResourceByCode(String code) throws Exception {
        Biller biller = billerRepository.findBillerByBillNo(code);
        BillerInfo billerInfo = BillerMapper.INSTANCE.billerToBillerInfo(biller);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(billerInfo,"","");
        return responseMessage;
    }
}
