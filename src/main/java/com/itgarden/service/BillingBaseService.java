package com.itgarden.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BillingBaseService<T, ID> {

    @Autowired
    private JpaRepository<T, ID> repository;

    public <S extends T> S save(S entity) {
        return repository.save(entity);
    }

    public List<T> findAll() {

        return repository.findAll();
    }

    public Optional<T> findById(ID id) {

        return repository.findById(id);
    }

    public void deleteById(ID id) {

        repository.deleteById(id);
    }

    public void delete(T type) {

        repository.delete(type);
    }
}
