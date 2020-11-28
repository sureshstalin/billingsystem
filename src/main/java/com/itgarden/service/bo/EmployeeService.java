package com.itgarden.service.bo;

import com.itgarden.dto.EmployeeInfo;
import com.itgarden.entity.Employee;
import com.itgarden.exception.ResourceNotFoundException;
import com.itgarden.mapper.EmployeeMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/*
 * Created by Suresh Stalin on 02 / Nov / 2020.
 */

@Service
public class EmployeeService  extends UserService{

    private final EmployeeRepository repository;

    @Autowired
    EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseMessage findResourceById(String id) throws Exception {

        UserDetails userDetails = getContext();

        Employee employee = null;
        if(id.contains("EMP")) {
            employee = repository.findByEmployeeCode(id);
        }
        else {
            employee = repository.findById(Long.parseLong(id)).orElse(null);
        }
        ResponseMessage responseMessage = new ResponseMessage();
        if (employee != null) {
            EmployeeInfo employeeDTO = EmployeeMapper.INSTANCE.employeeToEmployeeInfo(employee);
            responseMessage.setResponseClassType(employeeDTO);
        } else {
            throw new ResourceNotFoundException("Employee not found");
        }
        return responseMessage;
    }
}
