package com.itgarden.repository;

import com.itgarden.entity.BaseObject;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/*
 * Created by Suresh Stalin on 02 / Nov / 2020.
 */


public interface AbstractBaseRepository<T extends BaseObject, ID extends Serializable>
        extends JpaRepository<T, ID> {

}
