package com.itgarden.repository;

import com.itgarden.entity.AppEntityCode;
import com.itgarden.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Created by Suresh Stalin on 20 / Oct / 2020.
 */

public interface AppEntityCodeRepository extends JpaRepository<AppEntityCode,Long> {
    AppEntityCode findByCode(String code);
}
