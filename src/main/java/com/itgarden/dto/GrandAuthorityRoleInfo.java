package com.itgarden.dto;

import org.springframework.security.core.GrantedAuthority;

/*
 * Created by Suresh Stalin on 17 / Nov / 2020.
 */

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
