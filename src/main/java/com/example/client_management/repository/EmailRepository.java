package com.example.client_management.repository;

import com.example.client_management.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByClienteId(Long clienteId);
}