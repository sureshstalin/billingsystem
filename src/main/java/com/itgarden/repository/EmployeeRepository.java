package com.itgarden.repository;

import com.itgarden.entity.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>  {

        Employee findByEmployeeCode(String employeeCode);
}
