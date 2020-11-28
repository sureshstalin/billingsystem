package com.itgarden.repository;

import com.itgarden.entity.Customer;
import com.itgarden.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Created by Suresh Stalin on 02 / Nov / 2020.
 */

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByCustomerCode(String customerCode);
}
