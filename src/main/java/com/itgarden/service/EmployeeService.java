package com.itgarden.service;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.staticdata.ROLES;
import com.itgarden.common.staticdata.UserType;
import com.itgarden.dto.BaseInfo;
import com.itgarden.dto.EmployeeInfo;
import com.itgarden.entity.BaseObject;
import com.itgarden.entity.Employee;
import com.itgarden.entity.Role;
import com.itgarden.exception.InvalidInputException;
import com.itgarden.exception.ResourceNotFoundException;
import com.itgarden.mapper.EmployeeMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.EmployeeRepository;
import com.itgarden.repository.RoleRepository;
import com.itgarden.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Suresh Stalin on 02 / Nov / 2020.
 */

@Service
public class EmployeeService extends BaseService<EmployeeInfo> {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final CodeGenerator codeGenerator;

    @Autowired
    EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository,CodeGenerator codeGenerator) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.codeGenerator = codeGenerator;
    }

    @Override
    public ResponseMessage save(EmployeeInfo employeeInfo) {
        Employee employee = EmployeeMapper.INSTANCE.employeeInfoToEmployee(employeeInfo);
        Role role = roleRepository.findByName(ROLES.EMPLOYEE_ROLE.name()).orElse(null);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        employee.getUser().setRoles(roleList);
        if (employee.getId() == null) {
            employee.setEmployeeCode(codeGenerator.newCode(CodeType.EMPLOYEE_CODE));
        }
        employee.getUser().getAddressList().get(0).setUser(employee.getUser());
        employee.getUser().setUserType(UserType.EMPLOYEE.name());
//                employee.getUser().setPassword(passwordEncoder.encode(employee.getUser().getPassword()));
        employee.getUser().setPassword(employee.getUser().getPassword());
        BaseObject newObject = employeeRepository.save(employee); // Holds the reference of Employee object
        EmployeeInfo employeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeInfo((Employee) newObject);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(employeeDto, "Employee Created Successfully", "message");
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceByCode(String code) throws Exception {
        Employee employee = employeeRepository.findByEmployeeCode(code);
        ResponseMessage responseMessage = new ResponseMessage();
        if (employee != null) {
            EmployeeInfo employeeDTO = EmployeeMapper.INSTANCE.employeeToEmployeeInfo(employee);
            responseMessage.setResponseClassType(employeeDTO);
        } else {
            throw new ResourceNotFoundException("Employee not found");
        }
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceById(Long id) throws Exception {
        Employee employee = employeeRepository.findById(id).orElse(null);

        ResponseMessage responseMessage = new ResponseMessage();
        if (employee != null) {
            EmployeeInfo employeeDTO = EmployeeMapper.INSTANCE.employeeToEmployeeInfo(employee);
            responseMessage.setResponseClassType(employeeDTO);
        } else {
            throw new ResourceNotFoundException("Employee not found");
        }
        return responseMessage;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        return null;
    }

    public ResponseMessage<BaseInfo> createSuperAdmin(BaseInfo baseInfo) throws Exception {

        ResponseMessage<BaseInfo> responseMessage = null;
        String type = baseInfo.getType();
        try {
            if (type.equalsIgnoreCase(UserType.EMPLOYEE.name())) {
                Employee employee = EmployeeMapper.INSTANCE.employeeInfoToEmployee((EmployeeInfo) baseInfo);
                if (employee.getId() == null) {
                    employee.setEmployeeCode(codeGenerator.newCode(CodeType.EMPLOYEE_CODE));
                }
                employee.getUser().getAddressList().get(0).setUser(employee.getUser());
                employee.getUser().setUserType(UserType.EMPLOYEE.name());
//                employee.getUser().setPassword(passwordEncoder.encode(employee.getUser().getPassword()));
                employee.getUser().setPassword(employee.getUser().getPassword());
                BaseObject newObject = employeeRepository.save(employee); // Holds the reference of Employee object
                Role role = roleRepository.findByName(ROLES.SUPER_ADMIN_ROLE.name()).orElse(null);
                Employee employee1 = (Employee) newObject;
                ArrayList<Role> arrayList = new ArrayList<>();
                arrayList.add(role);
                employee1.getUser().setRoles(arrayList);
                employeeRepository.save(employee1);
                EmployeeInfo employeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeInfo((Employee) newObject);
                responseMessage = ResponseMessage.withResponseData(employeeDto, "Employee Created Successfully", "message");
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
