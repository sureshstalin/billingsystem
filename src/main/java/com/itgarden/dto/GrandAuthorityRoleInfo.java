package com.itgarden.dto;

import org.springframework.security.core.GrantedAuthority;

public class GrandAuthorityRoleInfo implements GrantedAuthority {

    private String roleName;

    public GrandAuthorityRoleInfo(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
