package com.itgarden.service.bo;

import com.itgarden.dto.CustomerDTO;
import com.itgarden.dto.EmployeeDTO;
import com.itgarden.entity.Customer;
import com.itgarden.entity.Employee;
import com.itgarden.exception.ResourceNotFoundException;
import com.itgarden.mapper.CustomerMapper;
import com.itgarden.mapper.EmployeeMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.CustomerRepository;
import com.itgarden.repository.EmployeeRepository;
import com.itgarden.service.bo.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmployeeService  {

    private final EmployeeRepository repository;

    @Autowired
    EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseMessage findEmployee(String id) throws Exception {
        Employee employee = null;
        if(id.contains("CUS")) {
            employee = repository.findByEmployeeCode(id);
        }
        else {
            employee = repository.findById(Long.parseLong(id)).orElse(null);
        }
        ResponseMessage responseMessage = new ResponseMessage();
        if (employee != null) {
            EmployeeDTO employeeDTO = EmployeeMapper.INSTANCE.employeeToDTO(employee);
            responseMessage.setResponseClassType(employeeDTO);
        } else {
            throw new ResourceNotFoundException("Employee not found");
        }
        return responseMessage;
    }

}
