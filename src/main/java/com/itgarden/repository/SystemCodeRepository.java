package com.itgarden.repository;

import com.itgarden.entity.SystemCodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemCodeRepository extends JpaRepository<SystemCodes, Long> {

    SystemCodes findByCodeType(String codeType);
}
