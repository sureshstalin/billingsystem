package com.itgarden.service.bo;

import com.itgarden.SystemCodeBean;
import com.itgarden.SystemCodeConfiguration;
import com.itgarden.common.CodeGenerator;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.staticdata.Constants;
import com.itgarden.common.staticdata.ROLES;
import com.itgarden.common.staticdata.UserType;
import com.itgarden.dto.OrganizationInfo;
import com.itgarden.dto.UserRoleInfo;
import com.itgarden.entity.Organization;
import com.itgarden.entity.Role;
import com.itgarden.exception.DuplicateKeyFoundException;
import com.itgarden.mapper.OrganizationMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.OrganizationRepository;
import com.itgarden.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService extends BaseService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private RoleService roleService;

    public ResponseMessage save(OrganizationInfo organizationInfo) throws Exception {
        ResponseMessage response = roleService.findResourceById(Constants.SUPER_ADMIN_ROLE_ID);
        UserRoleInfo userRoleInfo = (UserRoleInfo) response.getResponseClassType();
        if (userRoleInfo.getRoleId() == Long.parseLong(Constants.SUPER_ADMIN_ROLE_ID)) {
            throw new DuplicateKeyFoundException("The Super Admin already created: It can create only once.");
        }
        Organization organization = OrganizationMapper.INSTANCE
                .organizationInfoToOrganization(organizationInfo);
        String orgCode = codeGenerator.newCode(CodeType.ORG_CODE);
        organization.setOrgCode(orgCode);
        organization.getUser().setUserType(UserType.EMPLOYEE.name());
        List<Role> roles = new ArrayList<>();
        Role role = roleService.findByName(ROLES.SUPER_ADMIN_ROLE.name());
        role.setName(ROLES.SUPER_ADMIN_ROLE.name());
        role.setDescription("Super Admin Role");
        roles.add(role);
        organization.getUser().setRoles(roles);
        Organization newOrganization = organizationRepository.save(organization);

        OrganizationInfo newOrganizationInfo = OrganizationMapper.INSTANCE
                .organizationToOrganizationInfo(newOrganization);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(newOrganizationInfo,
                Constants.SUCCESS_STATUS,Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceById(String id) throws Exception {
        return null;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        return null;
    }
}
