package com.itgarden.service.bo;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.Utils;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.staticdata.ROLES;
import com.itgarden.common.staticdata.UserType;
import com.itgarden.dto.*;
import com.itgarden.entity.*;
import com.itgarden.exception.InvalidInputException;
import com.itgarden.mapper.CustomerMapper;
import com.itgarden.mapper.EmployeeMapper;
import com.itgarden.mapper.UserMapper;
import com.itgarden.mapper.VendorMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.RoleRepository;
import com.itgarden.service.BillingBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private BillingBaseService billingBaseService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${default.role}")
    private String defaultRole;



    public ResponseMessage<BaseDTO> doRegistration(BaseDTO baseDTO) throws Exception {

        ResponseMessage<BaseDTO> responseMessage = null;
        String type = baseDTO.getType();
        try {
            if (type.equalsIgnoreCase(UserType.EMPLOYEE.name())) {
                Employee employee = EmployeeMapper.INSTANCE.dtoToEmployee((EmployeeDTO) baseDTO);
                Role role = roleRepository.findByName(ROLES.EMPLOYEE_ROLE.name()).orElse(null);
                List<Role> roleList = new ArrayList<>();
                roleList.add(role);
                employee.getUser().setRoles(roleList);
                if(employee.getId() == null) {
                    employee.setEmployeeCode(codeGenerator.newCode(CodeType.EMPLOYEE_CODE));
                }
                employee.getUser().getAddressList().get(0).setUser(employee.getUser());
//                employee.getUser().setPassword(passwordEncoder.encode(employee.getUser().getPassword()));
                employee.getUser().setPassword(employee.getUser().getPassword());
                BaseObject newObject = billingBaseService.save(employee); // Holds the reference of Employee object
                EmployeeDTO employeeDto = EmployeeMapper.INSTANCE.employeeToDTO((Employee) newObject);
                responseMessage = ResponseMessage.withResponseData(employeeDto, "Employee Created Successfully", "message");
            }
            else if (type.equalsIgnoreCase(UserType.CUSTOMER.name())) {
                Customer customer =  CustomerMapper.INSTANCE.dtoToCustomer((CustomerDTO) baseDTO);
                Role role = roleRepository.findByName(ROLES.CUSTOMER_ROLE.name()).orElse(null);
                List<Role> roleList = new ArrayList<>();
                roleList.add(role);
                customer.getUser().setRoles(roleList);
                if(customer.getId() == null) {
                    customer.setCustomerCode(codeGenerator.newCode(CodeType.CUSTOMER_CODE));
                }
                customer.getUser().getAddressList().get(0).setUser(customer.getUser());
//                customer.getUser().setPassword(passwordEncoder.encode(customer.getUser().getPassword()));
                customer.getUser().setPassword(customer.getUser().getPassword());
                BaseObject newObject = billingBaseService.save(customer);
                CustomerDTO customerDto = CustomerMapper.INSTANCE.customerToDTO((Customer) newObject);
                responseMessage = ResponseMessage.withResponseData(customerDto, "Customer Created Successfully", "message");
            }
            else if (type.equalsIgnoreCase(UserType.VENDOR.name())) {
                Vendor vendor = VendorMapper.INSTANCE.dtoToVendor((VendorDTO)baseDTO);
                Role role = roleRepository.findByName(ROLES.VENDOR_ROLE.name()).orElse(null);
                List<Role> roleList = new ArrayList<>();
                roleList.add(role);
                vendor.getUser().setRoles(roleList);
                if(vendor.getId() == null) {
                    vendor.setVendorCode(codeGenerator.newCode(CodeType.VENDOR_CODE));
                }
//                vendor.getUser().setPassword(passwordEncoder.encode(vendor.getUser().getPassword()));
                vendor.getUser().setPassword(vendor.getUser().getPassword());
                vendor.getUser().getAddressList().get(0).setUser(vendor.getUser());
                BaseObject newObject = billingBaseService.save(vendor);
                VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToDTO((Vendor) newObject);
                responseMessage = ResponseMessage.withResponseData(vendorDTO, "Vendor Created Successfully", "message");
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(String.format("Invalid user type %s ", type));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return responseMessage;
    }


}

