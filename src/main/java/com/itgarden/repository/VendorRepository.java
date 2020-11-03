package com.itgarden.repository;

import com.itgarden.entity.Employee;
import com.itgarden.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long>  {

        Vendor findByVendorCode(String vendorCode);
}
