package com.example.client_management.repository;

 import com.example.client_management.entity.Cliente;
 import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}