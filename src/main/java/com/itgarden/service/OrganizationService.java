package com.itgarden.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Suresh Stalin on 24 / Nov / 2020.
 */

@Service
public class OrganizationService extends BaseService<OrganizationInfo> {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private RoleService roleService;

    public ResponseMessage save(OrganizationInfo organizationInfo)  throws Exception{
        ResponseMessage response = roleService.findResourceById(Constants.SUPER_ADMIN_ROLE_ID);
        UserRoleInfo userRoleInfo = (UserRoleInfo) response.getResponseClassType();
        if (userRoleInfo != null) {
            throw new DuplicateKeyFoundException
                    ("The Super Admin already created: can't create more than one Super Admin Role");
        }
        Organization organization = OrganizationMapper.INSTANCE
                .organizationInfoToOrganization(organizationInfo);

        String orgCode = codeGenerator.newCode(CodeType.ORG_CODE);
        organization.setOrgCode(orgCode);
        organization.getUser().getAddressList().get(0).setUser(organization.getUser());
        organization.getUser().setUserType(UserType.OWNER.name());
        List<Role> roles = new ArrayList<>();
        Role role = roleService.findByName(ROLES.SUPER_ADMIN_ROLE.name());
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
    public ResponseMessage findResourceByCode(String code) throws Exception {
        Organization organization = organizationRepository.findOrganizationByorgCode(code);
        OrganizationInfo organizationInfo = OrganizationMapper.INSTANCE.organizationToOrganizationInfo(organization);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(organizationInfo,"","");
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceById(Long id) throws Exception {
        Organization organization = organizationRepository.findById(id).orElse(null);
        OrganizationInfo organizationInfo = OrganizationMapper.INSTANCE.organizationToOrganizationInfo(organization);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(organizationInfo,"","");
        return responseMessage;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        List<OrganizationInfo> organizationInfos = new ArrayList<>();
        List<Organization> organizations = organizationRepository.findAll();
        for (Organization organization: organizations) {
            OrganizationInfo organizationInfo = OrganizationMapper.INSTANCE.organizationToOrganizationInfo(organization);
            organizationInfos.add(organizationInfo);
        }
        ResponseMessage responseMessage = ResponseMessage.withResponseData(organizationInfos,"","");
        return responseMessage;
    }
}
