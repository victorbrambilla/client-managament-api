package com.example.client_management.service;


import com.example.client_management.dto.ClienteDTO;
import com.example.client_management.entity.Cliente;
import com.example.client_management.enums.Status;
import com.example.client_management.exception.ResourceNotFoundException;
import com.example.client_management.mapper.ClienteMapper;
import com.example.client_management.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static com.example.client_management.specification.ClienteSpecification.*;


@Service
public class ClienteService {
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public Page<ClienteDTO> findAllWithFilters(int page, int size, String sortBy, String sortDirection, String name, String inscricao, Status status) {
        logger.info("Fetching clients with filters: page={}, size={}, sortBy={}, sortDirection={}, name={}, inscricao={}, status={}",
                page, size, sortBy, sortDirection, name, inscricao, status);

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);

        Specification<Cliente> spec = Specification.where(hasStatus(status))
                .and(hasName(name))
                .and(hasInscricao(inscricao));

        Page<Cliente> clientesPage = clienteRepository.findAll(spec, PageRequest.of(page, size, sort));

        return clientesPage.map(clienteMapper::toDTO);
    }

    public ClienteDTO findById(Long id) {
        logger.info("Fetching client with ID: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente with ID " + id + " not found"));
        return clienteMapper.toDTO(cliente);
    }

    public ClienteDTO create(ClienteDTO clienteDTO) {
        logger.info("Creating new client: {}", clienteDTO);
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente savedClient = clienteRepository.save(cliente);
        logger.info("Client created successfully with ID: {}", savedClient.getId());
        return clienteMapper.toDTO(savedClient);
    }

    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        logger.info("Updating client with ID: {}", id);
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente with ID " + id + " not found"));
        existingCliente.setInscricao(clienteDTO.getInscricao());
        existingCliente.setNome(clienteDTO.getNome());
        existingCliente.setApelido(clienteDTO.getApelido());
        existingCliente.setStatus(clienteDTO.getStatus());
        Cliente updatedClient = clienteRepository.save(existingCliente);
        logger.info("Client updated successfully: {}", updatedClient);
        return clienteMapper.toDTO(updatedClient);
    }

    public void delete(Long id) {
        logger.info("Deleting client with ID: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente with ID " + id + " not found"));
        logger.info("Client with ID {} deleted successfully", id);
        clienteRepository.delete(cliente);
    }
}
