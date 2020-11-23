package com.itgarden.service.bo;

import com.itgarden.dto.ProductInfo;
import com.itgarden.entity.Product;
import com.itgarden.mapper.ProductMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.service.BaseService;
import com.itgarden.service.BillingBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService {

    @Autowired
    private BillingBaseService billingBaseService;

    public ProductInfo save(ProductInfo productInfo) {

        Product product = ProductMapper.INSTANCE.productInfoToProduct(productInfo);
        Product newProduct = (Product)billingBaseService.save(product);
        ProductInfo productInfoResponse = ProductMapper.INSTANCE.productToProductInfo(newProduct);
        return productInfoResponse;
    }
    @Override
    public ResponseMessage findResourceById(String id) throws Exception {
        return null;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        return null;
    }
}
