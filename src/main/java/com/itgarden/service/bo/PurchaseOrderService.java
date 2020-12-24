package com.itgarden.service.bo;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.TaxCalculation;
import com.itgarden.common.TaxCalculationInput;
import com.itgarden.common.TaxCalculationResponse;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.staticdata.PurchaseOrderStatus;
import com.itgarden.dto.PurchaseOrderInfo;
import com.itgarden.entity.Category;
import com.itgarden.entity.PurchaseOrder;
import com.itgarden.entity.Tax;
import com.itgarden.entity.Vendor;
import com.itgarden.mapper.PurchaseOrderMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.CategoryRepository;
import com.itgarden.repository.PurchaseOrderRepository;
import com.itgarden.repository.TaxRepository;
import com.itgarden.repository.VendorRepository;
import com.itgarden.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderService extends BaseService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private TaxCalculation taxCalculation;

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseMessage<PurchaseOrderInfo> save(PurchaseOrderInfo purchaseOrderInfo) {

        PurchaseOrder purchaseOrder = PurchaseOrderMapper.INSTANCE
                .purchaseOrderInfoToPurchaseOrder(purchaseOrderInfo);
        if (purchaseOrderInfo.getId() == null) {
            Category category = categoryRepository.findById(purchaseOrderInfo.getCategory().getId()).orElse(null);
            purchaseOrder.setCategory(category);
            Vendor vendor = vendorRepository.getOne(purchaseOrder.getVendor().getId());
            purchaseOrder.setVendor(vendor);
            Tax tax = taxRepository.getOne(purchaseOrder.getTax().getId());
            purchaseOrder.setTax(tax);
            String purchaseOrderCode = codeGenerator.newCode(CodeType.PURCHASE_ORDER_CODE);
            purchaseOrder.setPurchaseOrderCode(purchaseOrderCode);
//            purchaseOrder.setPurchaseOrderStatus(PurchaseOrderStatus.PENDING);
        }
        TaxCalculationInput taxCalculationInput =
                new TaxCalculationInput(
                        purchaseOrder.getUnitPrice(), purchaseOrder.getTax().getTaxPercentage(), purchaseOrder.getQuantity());
        TaxCalculationResponse taxCalculationResponse
                = taxCalculation.calculateTax(taxCalculationInput);
        purchaseOrder.setTotalAmount(taxCalculationResponse.getTotalAmount());
        purchaseOrder.setGrandTotal(taxCalculationResponse.getGrandTotal());
        purchaseOrder.setTaxAmount(taxCalculationResponse.getTaxAmount());
        PurchaseOrder newPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        PurchaseOrderInfo newPurchaseOrderInfo = PurchaseOrderMapper
                .INSTANCE.purchaseOrderToPurchaseOrderInfo(newPurchaseOrder);
        ResponseMessage<PurchaseOrderInfo> responseMessage = ResponseMessage.withResponseData(newPurchaseOrderInfo, "", "");
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceById(String id) throws Exception {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(Long.parseLong(id)).orElse(null);
        PurchaseOrderInfo purchaseOrderInfo = PurchaseOrderMapper.INSTANCE.purchaseOrderToPurchaseOrderInfo(purchaseOrder);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(purchaseOrderInfo, "", "");
        return responseMessage;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        List<PurchaseOrderInfo> purchaseOrderInfos = new ArrayList<>();
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            PurchaseOrderInfo purchaseOrderInfo = PurchaseOrderMapper.INSTANCE.purchaseOrderToPurchaseOrderInfo(purchaseOrder);
            purchaseOrderInfos.add(purchaseOrderInfo);
        }
        ResponseMessage responseMessage = ResponseMessage.withResponseData(purchaseOrderInfos, "", "");
        return responseMessage;
    }

//    public void findPurchaseOrderByVendorAndProductNameAndStatus(Long vendorId,String productName,
//                                                                 PurchaseOrderStatus purchaseOrderStatus) {
//        PurchaseOrder purchaseOrder
//    }
}
