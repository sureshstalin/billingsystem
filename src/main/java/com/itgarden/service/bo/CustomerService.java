package com.itgarden.service.bo;

import com.itgarden.dto.CustomerInfo;
import com.itgarden.entity.Customer;
import com.itgarden.exception.ResourceNotFoundException;
import com.itgarden.mapper.CustomerMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/*
 * Created by Suresh Stalin on 02 / Nov / 2020.
 */

@Service
public class CustomerService extends UserService {

    private final CustomerRepository repository;

    @Autowired
    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    @Transactional
    @Override
    public ResponseMessage findResourceById(String id) throws Exception {
        Customer customer = null;
        if(id.contains("CUS")) {
            customer = repository.findByCustomerCode(id);
        }
        else {
            customer = repository.findById(Long.parseLong(id)).orElse(null);
        }
        ResponseMessage responseMessage = new ResponseMessage();
        if (customer != null) {
            CustomerInfo customerDTO = CustomerMapper.INSTANCE.customerToCustomerInfo(customer);
            responseMessage.setResponseClassType(customerDTO);
        } else {
            throw new ResourceNotFoundException("Customer not found");
        }
        return responseMessage;
    }
}
