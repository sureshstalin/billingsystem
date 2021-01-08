package com.itgarden.service;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.staticdata.Constants;
import com.itgarden.common.staticdata.PurchaseOrderStatus;
import com.itgarden.common.staticdata.STATUS;
import com.itgarden.dto.OfferInfo;
import com.itgarden.dto.ProductInfo;
import com.itgarden.dto.ProductItemInfo;
import com.itgarden.dto.ProductResponse;
import com.itgarden.entity.*;
import com.itgarden.exception.InvalidInputException;
import com.itgarden.exception.ResourceNotFoundException;
import com.itgarden.mapper.ProductItemMapper;
import com.itgarden.mapper.ProductMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.*;
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
public class ProductService extends BaseService<ProductInfo> {

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


    public ResponseMessage save(ProductInfo productInfo) throws Exception {
        ResponseMessage responseMessage = null;
        List<Offer> offers = new ArrayList<>();
        Product product = ProductMapper.INSTANCE.productInfoToProduct(productInfo);
        List<Offer> offerRequest = product.getOffers();
        List<Vendor> vendorRequest = product.getVendors();
        PurchaseOrder purchaseOrder = null;
        // As of now one vendor id from client side
        if (vendorRequest == null || vendorRequest.size() == 0) {
            throw new ResourceNotFoundException("Vendor id is not found. Vendor id is must");
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
            product.setStockCount(purchaseOrder.getQuantity());
            product.setCategory(purchaseOrder.getCategory());
            product.setDescription(purchaseOrder.getProductDescription());
            product.setName(purchaseOrder.getProductName());
            product.setTax(purchaseOrder.getTax());
            List<Vendor> vendors = new ArrayList<>();
            vendors.add(purchaseOrder.getVendor());
            product.setVendors(vendors);
            if (offerRequest != null) {
                for (Offer offerId : offerRequest) {
                    Offer offer = offerRepository.findById(offerId.getId()).orElse(null);
                    if (!offer.getStatus().equalsIgnoreCase(STATUS.ACTIVE.name())) {
                        throw new InvalidInputException(String.format("The offer you are associating is not valid: %s", offer.getOfferName()));
                    }
                    offers.add(offer);
                }
            }
            product.setOffers(offers);
            product.setVendors(vendors);
        }
        Product newProduct = (Product) productRepository.save(product);
        productInfo =  ProductMapper.INSTANCE.productToProductInfo(newProduct);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductInfo(productInfo);
        if (newProduct.getStockCount() > 0) {
            responseMessage = productItemService.save(newProduct);
            purchaseOrder.setPurchaseOrderStatus(PurchaseOrderStatus.COMPLETED);
            purchaseOrderRepository.save(purchaseOrder);
            List<ProductItem> productItems = (List<ProductItem>)responseMessage.getResponseClassType();
            System.out.println("ProductItems " + productItems);
            List<ProductItemInfo> productItemInfos = new ArrayList<>();
            for (ProductItem productItem: productItems) {
                ProductItemInfo productItemInfo = ProductItemMapper.INSTANCE.productItemToProductItemInfo(productItem);
                productItemInfos.add(productItemInfo);
            }
             productResponse.setProductItemList(productItemInfos);
            responseMessage = ResponseMessage.withResponseData(productResponse, "Product saved Successfully", Constants.INFO_TYPE);
        } else {
            productResponse.setProductInfo(productInfo);
            responseMessage = ResponseMessage.withResponseData(productResponse, "Product saved Successfully", Constants.INFO_TYPE);
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
    public ResponseMessage findResourceById(Long id) throws Exception {
        Product product = productRepository.findById(id).orElse(null);
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

    @Override
    public ResponseMessage findResourceByCode(String code) throws Exception {
        return null;
    }
}
