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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseMessage findCustomer(String id) throws Exception {
        Customer customer = null;
        if(id.contains("CUS")) {
            customer = repository.findByCustomerCode(id);
        }
        else {
            customer = repository.findById(Long.parseLong(id)).orElse(null);
        }
        ResponseMessage responseMessage = new ResponseMessage();
        if (customer != null) {
            CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToDTO(customer);
            responseMessage.setResponseClassType(customerDTO);
        } else {
            throw new ResourceNotFoundException("Customer not found");
        }
        return responseMessage;
    }

}
