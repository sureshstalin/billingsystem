package com.itgarden.service.bo;

import com.itgarden.dto.BaseDto;
import com.itgarden.dto.EmployeeDto;
import com.itgarden.dto.ResponseMessage;
import com.itgarden.dto.UserDto;
import com.itgarden.entity.Employee;
import com.itgarden.entity.User;
import com.itgarden.mapper.EmployeeMapper;
import com.itgarden.mapper.UserMapper;
import com.itgarden.service.BillingBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private BillingBaseService<Employee,Long> billingBaseService;

    public ResponseMessage<BaseDto> doRegistration(BaseDto baseDto) {

        ResponseMessage<BaseDto> responseMessage = null;
        String flowType = baseDto.getFlowType();
        if(flowType.equalsIgnoreCase("employee")) {
            UserDto userDto = (UserDto)baseDto;
            User user = UserMapper.INSTANCE.userDTOtoUser(userDto);
            Employee employee = new Employee();
            employee.setFullName(new StringBuilder(user.getFirstName()).append(" ").append(user.getLastName()).toString());
            employee.setEmployeeCode("EMP001");
            employee.setUser(user);
            Employee newEmployee = billingBaseService.save(employee);
            EmployeeDto employeeDto = EmployeeMapper.INSTANCE.employeeToDTO(newEmployee);
            responseMessage = ResponseMessage.withResponseData(employeeDto);
        }
        return responseMessage;
    }
}
