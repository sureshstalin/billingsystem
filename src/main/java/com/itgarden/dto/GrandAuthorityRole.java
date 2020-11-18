package com.itgarden.dto;

import org.springframework.security.core.GrantedAuthority;

public class GrandAuthorityRole implements GrantedAuthority {

    private String roleName;

    public GrandAuthorityRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
