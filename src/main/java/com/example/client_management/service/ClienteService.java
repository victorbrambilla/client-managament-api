package com.example.client_management.service;


import com.example.client_management.dto.ClienteDTO;
import com.example.client_management.entity.Cliente;
import com.example.client_management.exception.ResourceNotFoundException;
import com.example.client_management.mapper.ClienteMapper;
import com.example.client_management.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente with ID " + id + " not found"));
        return clienteMapper.toDTO(cliente);
    }

    public ClienteDTO create(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        return clienteMapper.toDTO(clienteRepository.save(cliente));
    }

    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente with ID " + id + " not found"));
        existingCliente.setInscricao(clienteDTO.getInscricao());
        existingCliente.setNome(clienteDTO.getNome());
        existingCliente.setApelido(clienteDTO.getApelido());
        existingCliente.setStatus(clienteDTO.getStatus());
        return clienteMapper.toDTO(clienteRepository.save(existingCliente));
    }

    public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente with ID " + id + " not found"));
        clienteRepository.delete(cliente);
    }
}
