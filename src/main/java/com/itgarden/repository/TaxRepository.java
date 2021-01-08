package com.itgarden.repository;

import com.itgarden.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

public interface TaxRepository extends JpaRepository<Tax,Long> {


}
