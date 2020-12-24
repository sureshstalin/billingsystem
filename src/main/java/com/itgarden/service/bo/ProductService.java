package com.itgarden.service.bo;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.staticdata.Constants;
import com.itgarden.common.staticdata.PurchaseOrderStatus;
import com.itgarden.common.staticdata.STATUS;
import com.itgarden.dto.OfferInfo;
import com.itgarden.dto.ProductInfo;
import com.itgarden.entity.*;
import com.itgarden.exception.InvalidInputException;
import com.itgarden.exception.ResourceNotFoundException;
import com.itgarden.mapper.ProductMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.*;
import com.itgarden.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Created by Suresh Stalin on 22 / Nov / 2020.
 */

@Service
public class ProductService extends BaseService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ProductItemService productItemService;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;


    public ResponseMessage add(ProductInfo productInfo) {
        ResponseMessage responseMessage = null;
        List<Offer> offers = new ArrayList<>();
        List<Vendor> vendors = new ArrayList<>();
        Product product = ProductMapper.INSTANCE.productInfoToProduct(productInfo);
        List<Offer> offerRequest = product.getOffers();
        List<Vendor> vendorRequest = product.getVendors();
        PurchaseOrder purchaseOrder = null;
        // As of now one vendor id from client side
        if (vendorRequest == null || vendorRequest.size() == 0) {
            throw new InvalidInputException("Vendor id is not found. Vendor id is must");
        }
        if (StringUtils.isEmpty(product.getProductCode())) {
            purchaseOrder = purchaseOrderRepository.findPurchaseOrderByVendorAndProductNameAndPurchaseOrderStatus(
                    vendorRequest.get(0), product.getName(), PurchaseOrderStatus.READY);
            if (purchaseOrder == null) {
                throw new ResourceNotFoundException(String.format("There is no Purchase Order in Ready state for Vendor %d and Product Name %s ",
                        vendorRequest.get(0).getId(), product.getName()));
            }
            String productCode = codeGenerator.newCode(CodeType.PRODUCT_CODE);
            product.setProductCode(productCode);
            Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
            Tax tax = taxRepository.findById(product.getTax().getId()).orElse(null);
            if (offerRequest != null) {
                for (Offer offerId : offerRequest) {
                    Offer offer = offerRepository.findById(offerId.getId()).orElse(null);
                    if (!offer.getStatus().equalsIgnoreCase(STATUS.ACTIVE.name())) {
                        throw new InvalidInputException(String.format("The offer you are associating is not valid: %s", offer.getOfferName()));
                    }
                    offers.add(offer);
                }
            }
            for (Vendor vendorReq : vendorRequest) {
                Vendor vendor = vendorRepository.findById(vendorReq.getId()).orElse(null);
                vendors.add(vendor);
//                productStockCount = vendor.get
            }
            product.setStockCount(purchaseOrder.getQuantity());
            if (vendors.isEmpty()) {
                throw new ResourceNotFoundException("Vendor not found");
            }
            if (category == null) {
                throw new ResourceNotFoundException("Category not found");
            }
            if (tax == null) {
                throw new ResourceNotFoundException("Tax not found");
            }
            product.setOffers(offers);
            product.setVendors(vendors);
            product.setCategory(category);
            product.setTax(tax);
        }
        Product newProduct = (Product) productRepository.save(product);
        if (newProduct.getStockCount() > 0) {
            responseMessage = productItemService.save(newProduct);
            purchaseOrder.setPurchaseOrderStatus(PurchaseOrderStatus.COMPLETED);
            purchaseOrderRepository.save(purchaseOrder);
        } else {
            responseMessage = ResponseMessage.withResponseData(newProduct, "Product saved Successfully", Constants.INFO_TYPE);
        }
        return responseMessage;
    }

    public ResponseMessage update(ProductInfo productInfo) {
        ResponseMessage responseMessage = null;
        Product product = ProductMapper.INSTANCE.productInfoToProduct(productInfo);
        Vendor vendor = product.getVendors().get(0);
        List<OfferInfo> offerInfos = productInfo.getOffers();
//        if(offerInfos)
        List<Long> offerIds = productInfo.getOffers().stream()
                .map(OfferInfo::getId)
                .collect(Collectors.toList());
        List<Offer> offers = offerRepository.findOfferByIdIn(offerIds);
        product.setOffers(offers);
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findPurchaseOrderByVendorAndProductNameAndPurchaseOrderStatus(
                product.getVendors().get(0), product.getName(), PurchaseOrderStatus.READY);
        if(purchaseOrder != null && purchaseOrder.getQuantity() > 0) {
            product.setStockCount(product.getStockCount() + purchaseOrder.getQuantity());
            purchaseOrder.setPurchaseOrderStatus(PurchaseOrderStatus.COMPLETED);
        }
        Product newProduct = (Product) productRepository.save(product);
        productItemService.save(newProduct,purchaseOrder.getQuantity());
        if(purchaseOrder != null && purchaseOrder.getPurchaseOrderStatus().equals(PurchaseOrderStatus.COMPLETED)){
            purchaseOrderRepository.save(purchaseOrder);
        }
        ProductInfo newProductInfo = ProductMapper.INSTANCE.productToProductInfo(newProduct);
        responseMessage = ResponseMessage.withResponseData(newProductInfo, "Product saved Successfully", Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceById(String id) throws Exception {
        Product product = productRepository.findById(Long.parseLong(id)).orElse(null);
        ProductInfo productInfo = ProductMapper.INSTANCE.productToProductInfo(product);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(productInfo, Constants.SUCCESS_STATUS, Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        List<ProductInfo> productInfos = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            ProductInfo productInfo = ProductMapper.INSTANCE.productToProductInfo(product);
            productInfos.add(productInfo);
        }
        ResponseMessage responseMessage = ResponseMessage.withResponseData(productInfos, Constants.SUCCESS_STATUS, Constants.INFO_TYPE);
        return responseMessage;
    }
}
