package com.itgarden.repository;

import com.itgarden.entity.Biller;
import com.itgarden.entity.Payment;
import com.itgarden.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findPaymentByProductItem(ProductItem productItem);

    List<Payment> findPaymentByBiller(Biller biller);

}
