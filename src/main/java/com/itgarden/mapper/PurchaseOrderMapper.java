package com.itgarden.mapper;

import com.itgarden.dto.ProductItemInfo;
import com.itgarden.dto.PurchaseOrderInfo;
import com.itgarden.entity.ProductItem;
import com.itgarden.entity.PurchaseOrder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface PurchaseOrderMapper {

    PurchaseOrderMapper INSTANCE =  Mappers.getMapper(PurchaseOrderMapper.class);
    PurchaseOrder purchaseOrderInfoToPurchaseOrder(PurchaseOrderInfo purchaseOrderInfo);
    @InheritInverseConfiguration
    PurchaseOrderInfo purchaseOrderToPurchaseOrderInfo(PurchaseOrder purchaseOrder);
}
