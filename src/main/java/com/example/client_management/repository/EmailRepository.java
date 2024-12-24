package com.example.client_management.repository;

import com.example.client_management.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query("SELECT e FROM Email e JOIN FETCH e.categoria WHERE e.cliente.id = :clienteId")
    List<Email> findByClienteId(Long clienteId);
}