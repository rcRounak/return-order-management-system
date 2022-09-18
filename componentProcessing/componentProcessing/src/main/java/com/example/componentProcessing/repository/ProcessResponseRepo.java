package com.example.componentProcessing.repository;

import com.example.componentProcessing.model.ProcessResponse;
import org.springframework.data.repository.CrudRepository;

public interface ProcessResponseRepo extends CrudRepository<ProcessResponse, Long> {
    public boolean existsById(Long id);
}
