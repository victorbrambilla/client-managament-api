package com.example.client_management.service;

import com.example.client_management.dto.EmailDTO;
import com.example.client_management.entity.Email;
import com.example.client_management.exception.ResourceNotFoundException;
import com.example.client_management.mapper.EmailMapper;
import com.example.client_management.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;
    @Autowired
    public EmailService(EmailRepository emailRepository, EmailMapper emailMapper) {
        this.emailRepository = emailRepository;
        this.emailMapper = emailMapper;
    }

    public List<EmailDTO> getAllEmailsByClientId(Long clienteId) {
        logger.info("Fetching all emails for client with ID: {}", clienteId);
        return emailRepository.findByClienteId(clienteId)
                .stream()
                .map(emailMapper::toDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    public EmailDTO findById(Long id) {
        logger.info("Fetching email with ID: {}", id);
        Email email = emailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Email with ID " + id + " not found"));
        return emailMapper.toDTO(email);
    }

    public EmailDTO create(EmailDTO emailDTO) {
        logger.info("Creating new email: {}", emailDTO);
        Email email = emailMapper.toEntity(emailDTO);
        Email savedEmail = emailRepository.save(email);
        logger.info("Email created successfully with ID: {}", savedEmail.getId());
        return emailMapper.toDTO(savedEmail);
    }

    public EmailDTO update(Long id, EmailDTO emailDTO) {
        logger.info("Updating email with ID: {}", id);
        Email email = emailRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Email with ID " + id + " not found"));
        email.setEmail(emailDTO.getEmail());
        Email updatedEmail = emailRepository.save(email);
        logger.info("Email updated successfully: {}", updatedEmail);
        return emailMapper.toDTO(updatedEmail);
    }

    public void delete(Long id) {
        logger.info("Deleting email with ID: {}", id);
        Email email = emailRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Email with ID " + id + " not found"));
        emailRepository.delete(email);
        logger.info("Email with ID {} deleted successfully", id);
    }
}
