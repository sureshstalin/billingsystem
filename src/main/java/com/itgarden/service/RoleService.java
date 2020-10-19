package com.itgarden.service;

import com.itgarden.entity.Role;
import com.itgarden.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BillingBaseService<Role,Long> {

    @Autowired
    RoleRepository roleRepository;

    public Role findByName(String name) {
        Role role = roleRepository.findByName(name).orElse(null);
        return role;
    }
}
