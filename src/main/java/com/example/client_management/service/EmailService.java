package com.example.client_management.service;

import com.example.client_management.dto.EmailDTO;
import com.example.client_management.entity.Email;
import com.example.client_management.exception.ResourceNotFoundException;
import com.example.client_management.mapper.EmailMapper;
import com.example.client_management.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;
    @Autowired
    public EmailService(EmailRepository emailRepository, EmailMapper emailMapper) {
        this.emailRepository = emailRepository;
        this.emailMapper = emailMapper;
    }

    public List<EmailDTO> getAllEmailsByClientId(Long clienteId) {
        return emailRepository.findByClienteId(clienteId)
                .stream()
                .map(emailMapper::toDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    public EmailDTO findById(Long id) {
        Email email = emailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Email with ID " + id + " not found"));
        return emailMapper.toDTO(email);
    }

    public EmailDTO create(EmailDTO emailDTO) {
        Email email = emailMapper.toEntity(emailDTO);
        return emailMapper.toDTO(emailRepository.save(email));
    }

    public EmailDTO update(Long id, EmailDTO emailDTO) {
        Email email = emailRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Email with ID " + id + " not found"));
        email.setEmail(emailDTO.getEmail());
        return emailMapper.toDTO(emailRepository.save(email));
    }

    public void delete(Long id) {
        Email email = emailRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Email with ID " + id + " not found"));
        emailRepository.delete(email);
    }
}
