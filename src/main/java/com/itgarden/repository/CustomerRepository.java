package com.itgarden.repository;

import com.itgarden.entity.Customer;
import com.itgarden.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByCustomerCode(String customerCode);
}
