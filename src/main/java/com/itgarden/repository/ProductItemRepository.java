package com.itgarden.repository;

import com.itgarden.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * Created by Suresh Stalin on 24 / Nov / 2020.
 */

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

    List<ProductItem> findProductItemByProductItemCodeIn(List<String> productItemCodes);
}
