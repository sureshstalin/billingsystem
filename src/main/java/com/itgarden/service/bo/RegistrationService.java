package com.itgarden.service.bo;

import com.itgarden.common.Constants;
import com.itgarden.common.Utils;
import com.itgarden.dto.*;
import com.itgarden.entity.*;
import com.itgarden.mapper.CustomerMapper;
import com.itgarden.mapper.EmployeeMapper;
import com.itgarden.mapper.UserMapper;
import com.itgarden.mapper.VendorMapper;
import com.itgarden.service.BillingBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private BillingBaseService billingBaseService;

    public ResponseMessage<BaseDTO> doRegistration(BaseDTO baseDto) {

        ResponseMessage<BaseDTO> responseMessage = null;
        String flowType = baseDto.getFlowType();
        UserDTO userDto = (UserDTO)baseDto;
        User user = UserMapper.INSTANCE.userDTOtoUser(userDto);
        if(flowType.equalsIgnoreCase(Constants.EMPLOYEE_FLOW_TYPE)) {
            Employee employee = new Employee();
            employee.setFullName(Utils.getFullName(user.getFirstName(),user.getMiddleName(),
                    user.getLastName()));
            employee.setEmployeeCode("EMP001");
            employee.setUser(user);
            user.getAddressList().get(0).setUser(user);
            BaseObject newObject = billingBaseService.save(employee);
            EmployeeDTO employeeDto = EmployeeMapper.INSTANCE.employeeToDTO((Employee)newObject);
            responseMessage = ResponseMessage.withResponseData(employeeDto);
        }else if(flowType.equalsIgnoreCase(Constants.CUSTOMER_FLOW_TYPE) ) {
            Customer customer = new Customer();
            customer.setCustomerCode("CUS001");
            customer.setFullName(Utils.getFullName(user.getFirstName(),user.getMiddleName(),
                    user.getLastName()));
            customer.setUser(user);
            user.getAddressList().get(0).setUser(user);
            BaseObject newObject = billingBaseService.save(customer);
            CustomerDTO customerDto = CustomerMapper.INSTANCE.customerToDTO((Customer) newObject);
            responseMessage = ResponseMessage.withResponseData(customerDto);
        }else if(flowType.equalsIgnoreCase(Constants.VENDOR_FLOW_TYPE)) {
            Vendor vendor = new Vendor();
            vendor.setFullName(Utils.getFullName(user.getFirstName(),
                    user.getMiddleName(),user.getLastName()));
            vendor.setVendorCode("VN0001");
            vendor.setUser(user);
            user.getAddressList().get(0).setUser(user);
            BaseObject newObject = billingBaseService.save(vendor);
            VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO((Vendor)newObject);
            responseMessage = ResponseMessage.withResponseData(vendorDTO);
        }
        return responseMessage;
    }


}

