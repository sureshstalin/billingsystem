package com.itgarden.service.bo;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.Utils;
import com.itgarden.common.staticdata.UserType;
import com.itgarden.dto.*;
import com.itgarden.entity.*;
import com.itgarden.mapper.CustomerMapper;
import com.itgarden.mapper.EmployeeMapper;
import com.itgarden.mapper.UserMapper;
import com.itgarden.mapper.VendorMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.RoleRepository;
import com.itgarden.service.BillingBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private BillingBaseService billingBaseService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CodeGenerator codeGenerator;

    @Value("${default.role}")
    private String defaultRole;

    public ResponseMessage<BaseDTO> doRegistration(UserDTO userDTO) {

        ResponseMessage<BaseDTO> responseMessage = null;
        String type = userDTO.getType(); // employee
        User user = UserMapper.INSTANCE.userDTOtoUser(userDTO);
        Role role = roleRepository.findByName(defaultRole).orElse(null);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoles(roleList);
        if(type.equalsIgnoreCase(UserType.EMPLOYEE.name())) {
            Employee employee = new Employee();
            employee.setFullName(Utils.getFullName(user.getFirstName(),user.getMiddleName(),
                    user.getLastName()));
            employee.setEmployeeCode(codeGenerator.newCode(CodeType.EMPLOYEE_CODE));
            employee.setUser(user);
            user.getAddressList().get(0).setUser(user);

            BaseObject newObject = billingBaseService.save(employee); // Holds the reference of Employee object
            EmployeeDTO employeeDto = EmployeeMapper.INSTANCE.employeeToDTO((Employee)newObject);
            responseMessage = ResponseMessage.withResponseData(employeeDto,"Employee Created Successfully","message");
        }else if(type.equalsIgnoreCase(UserType.CUSTOMER.name())) {
            Customer customer = new Customer();
            customer.setCustomerCode(codeGenerator.newCode(CodeType.CUSTOMER_CODE));
            customer.setFullName(Utils.getFullName(user.getFirstName(),user.getMiddleName(),
                    user.getLastName()));
            customer.setUser(user);
            user.getAddressList().get(0).setUser(user);
            BaseObject newObject = billingBaseService.save(customer);
            CustomerDTO customerDto = CustomerMapper.INSTANCE.customerToDTO((Customer) newObject);
            responseMessage = ResponseMessage.withResponseData(customerDto,"Customer Created Successfully","message");
        }else if(type.equalsIgnoreCase(UserType.VENDOR.name())) {
            Vendor vendor = new Vendor();
            vendor.setFullName(Utils.getFullName(user.getFirstName(),
                    user.getMiddleName(),user.getLastName()));
            vendor.setVendorCode(codeGenerator.newCode(CodeType.VENDOR_CODE));
            vendor.setUser(user);
            user.getAddressList().get(0).setUser(user);
            BaseObject newObject = billingBaseService.save(vendor);
            VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO((Vendor)newObject);
            responseMessage = ResponseMessage.withResponseData(vendorDTO,"Vendor Created Successfully","message");
        }
        return responseMessage;
    }


}

