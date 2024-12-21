package com.example.client_management.service;


import com.example.client_management.dto.ClienteDTO;
import com.example.client_management.entity.Cliente;
import com.example.client_management.enums.Status;
import com.example.client_management.exception.ResourceNotFoundException;
import com.example.client_management.mapper.ClienteMapper;
import com.example.client_management.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static com.example.client_management.specification.ClienteSpecification.*;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public Page<ClienteDTO> findAllWithFilters(int page, int size, String sortBy, String sortDirection, String name, String inscricao, Status status) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);

        Specification<Cliente> spec = Specification.where(hasStatus(status))
                .and(hasName(name))
                .and(hasInscricao(inscricao));

        Page<Cliente> clientesPage = clienteRepository.findAll(spec, PageRequest.of(page, size, sort));

        return clientesPage.map(clienteMapper::toDTO);
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
