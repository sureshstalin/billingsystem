package com.itgarden.repository;

import com.itgarden.common.staticdata.PurchaseOrderStatus;
import com.itgarden.entity.PurchaseOrder;
import com.itgarden.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {

    PurchaseOrder findPurchaseOrderByVendorAndProductNameAndPurchaseOrderStatus
            (Vendor vendor, String productName, PurchaseOrderStatus purchaseOrderStatus);
}
