package com.itgarden.common;

import com.itgarden.entity.Employee;
import com.itgarden.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CodeGenerator {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String newCode() {
        String employeeCode = "";
        Employee employee = null;
        do {
            String id =  getCode();
            employee = employeeRepository.findByEmployeeCode(id);
            if(employee == null) {
                employeeCode = id;
            }
        }while(employee != null);
        return employeeCode;
    }

    private String getCode() {
        Random r = new Random( System.currentTimeMillis() );
        int id =  ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        return "EMP " + id;
    }
}
