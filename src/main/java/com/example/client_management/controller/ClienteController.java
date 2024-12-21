package com.example.client_management.controller;

import com.example.client_management.dto.ClienteDTO;
import com.example.client_management.entity.Cliente;
import com.example.client_management.enums.Status;
import com.example.client_management.service.ClienteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Clientes", description = "Gerenciamento dos recursos de Clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Retorna lista de clientes filtrada e paginada")
    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> getAllClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String inscricao,
            @RequestParam(required = false) Status status) {
        Page<ClienteDTO> clients = clienteService.findAllWithFilters(page, size, sortBy, sortDirection, name, inscricao, status);
        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Retorna cliente pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @Operation(summary = "Cria um novo cliente")
    @PostMapping
    public ResponseEntity<ClienteDTO> createClient(@Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.create(clienteDTO));
    }

    @Operation(summary = "Atualiza um cliente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.update(id, clienteDTO));
    }

    @Operation(summary = "Remove um cliente pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
