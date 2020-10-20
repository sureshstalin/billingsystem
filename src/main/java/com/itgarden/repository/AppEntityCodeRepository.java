package com.itgarden.repository;

import com.itgarden.entity.AppEntityCode;
import com.itgarden.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppEntityCodeRepository extends JpaRepository<AppEntityCode,Long> {
    AppEntityCode findByCode(String code);
}
