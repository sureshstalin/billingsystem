package com.itgarden.service.bo;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.staticdata.Constants;
import com.itgarden.dto.ProductInfo;
import com.itgarden.entity.*;
import com.itgarden.mapper.ProductMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.*;
import com.itgarden.service.BaseService;
import com.itgarden.service.BillingBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

        Product product = ProductMapper.INSTANCE.productInfoToProduct(productInfo);
        List<Offer> offers = product.getOffers();
        List<Vendor> vendors = product.getVendors();
        if (StringUtils.isEmpty(product.getProductCode())) {
            String productCode = codeGenerator.newCode(CodeType.PRODUCT_CODE);
            product.setProductCode(productCode);
            Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
            Tax tax = taxRepository.findById(product.getTax().getId()).orElse(null);
            Offer offer = offerRepository.findById(offers.get(0).getId()).orElse(null);
            offers = new ArrayList<>();
            offers.add(offer);
            Vendor vendor = vendorRepository.findById(vendors.get(0).getId()).orElse(null);
            vendors = new ArrayList<>();
            vendors.add(vendor);
            product.setOffers(offers);
            product.setVendors(vendors);
            product.setCategory(category);
            product.setTax(tax);
        }
        Product newProduct = (Product) productRepository.save(product);
        ResponseMessage responseMessage = productItemService.save(newProduct);
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceById(String id) throws Exception {
        Product product = productRepository.findById(Long.parseLong(id)).orElse(null);
        ProductInfo productInfo = ProductMapper.INSTANCE.productToProductInfo(product);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(productInfo, Constants.SUCCESS_STATUS,Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        List<ProductInfo> productInfos = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product: products) {
            ProductInfo productInfo = ProductMapper.INSTANCE.productToProductInfo(product);
            productInfos.add(productInfo);
        }
        ResponseMessage responseMessage = ResponseMessage.withResponseData(productInfos,Constants.SUCCESS_STATUS,Constants.INFO_TYPE);
        return responseMessage;
    }
}
