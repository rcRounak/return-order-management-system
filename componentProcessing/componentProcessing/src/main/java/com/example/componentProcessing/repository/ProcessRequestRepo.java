package com.example.componentProcessing.repository;

import com.example.componentProcessing.model.ProcessRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRequestRepo extends JpaRepository<ProcessRequest, String> {
}
