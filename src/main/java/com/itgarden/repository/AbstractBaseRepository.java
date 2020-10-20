package com.itgarden.repository;

import com.itgarden.entity.BaseObject;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface AbstractBaseRepository<T extends BaseObject, ID extends Serializable>
        extends JpaRepository<T, ID> {

}
