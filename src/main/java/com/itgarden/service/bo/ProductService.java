package com.itgarden.service.bo;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.staticdata.Constants;
import com.itgarden.common.staticdata.STATUS;
import com.itgarden.dto.ProductInfo;
import com.itgarden.entity.*;
import com.itgarden.exception.InvalidInputException;
import com.itgarden.mapper.ProductMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.*;
import com.itgarden.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    public ResponseMessage save(ProductInfo productInfo) {
        ResponseMessage responseMessage = null;
        List<Offer> offers = new ArrayList<>();
        List<Vendor> vendors = new ArrayList<>();
        Product product = ProductMapper.INSTANCE.productInfoToProduct(productInfo);
        List<Offer> offerRequest = product.getOffers();
        List<Vendor> vendorRequest = product.getVendors();
        if (StringUtils.isEmpty(product.getProductCode())) {
            String productCode = codeGenerator.newCode(CodeType.PRODUCT_CODE);
            product.setProductCode(productCode);
            Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
            Tax tax = taxRepository.findById(product.getTax().getId()).orElse(null);
            for (Offer offerId : offerRequest) {
                Offer offer = offerRepository.findById(offerId.getId()).orElse(null);
                if (!offer.getStatus().equalsIgnoreCase(STATUS.ACTIVE.name())) {
                    throw new InvalidInputException(String.format("The offer you are associating is not valid: %s", offer.getOfferName()));
                }
                offers.add(offer);
            }
            for (Vendor vendorId : vendorRequest) {
                Vendor vendor = vendorRepository.findById(vendorId.getId()).orElse(null);
                vendors.add(vendor);
            }
            product.setOffers(offers);
            product.setVendors(vendors);
            product.setCategory(category);
            product.setTax(tax);
        }
        Product newProduct = (Product) productRepository.save(product);
        if(newProduct.getStockCount() > 0) {
            responseMessage = productItemService.save(newProduct);
        }else{
            responseMessage = ResponseMessage.withResponseData(newProduct,"Product saved Successfully",Constants.INFO_TYPE);
        }
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
