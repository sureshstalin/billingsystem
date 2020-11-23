package com.itgarden.repository;

import com.itgarden.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax,Long> {
}
