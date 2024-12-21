package com.example.client_management.controller;

import com.example.client_management.dto.EmailDTO;
import com.example.client_management.entity.Email;
import com.example.client_management.service.EmailService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "E-mails", description = "Gerenciamento dos recursos de E-mail")
@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(summary = "Retorna todos os e-mails de um cliente pelo ID")
    @GetMapping("/cliente/{clienteId}")
    public List<EmailDTO> getAllEmailsByClientId(@PathVariable Long clienteId) {
        return emailService.getAllEmailsByClientId(clienteId);
    }

    @Operation(summary = "Retorna o e-mail pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmailDTO> getEmailById(@PathVariable Long id) {
        return ResponseEntity.ok(emailService.findById(id));
    }

    @Operation(summary = "Cria um novo e-mail")
    @PostMapping
    public ResponseEntity<EmailDTO> createEmail(@Valid @RequestBody EmailDTO emailDTO) {
        return ResponseEntity.ok(emailService.create(emailDTO));
    }

    @Operation(summary = "Atualiza um e-mail pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<EmailDTO> updateEmail(@PathVariable Long id, @Valid @RequestBody EmailDTO emailDTO) {
        return ResponseEntity.ok(emailService.update(id, emailDTO));
    }

    @Operation(summary = "Remove um e-mail pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        emailService.delete(id);
        return ResponseEntity.noContent().build();
    }
}