package com.itgarden.repository;

import com.itgarden.entity.Role;
import com.itgarden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);
}
