package com.example.client_management.service;

import com.example.client_management.dto.CategoriaDTO;
import com.example.client_management.dto.EmailDTO;
import com.example.client_management.entity.Categoria;
import com.example.client_management.exception.ResourceNotFoundException;
import com.example.client_management.mapper.CategoriaMapper;
import com.example.client_management.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import com.example.client_management.exception.EntityInUseException;

@Service
public class CategoriaService {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaService.class);

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;
    private final EmailService emailService;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper CategoriaMapper, EmailService emailService) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = CategoriaMapper;
        this.emailService = emailService;
    }

    public List<CategoriaDTO> findAll() {
        logger.info("Fetching all categories");
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO findById(Long id) {
        logger.info("Fetching category with ID: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria with ID " + id + " not found"));
        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO create(CategoriaDTO categoriaDTO) {
        logger.info("Creating new category: {}", categoriaDTO);
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        logger.info("Category created successfully with ID: {}", savedCategoria.getId());
        return categoriaMapper.toDTO(savedCategoria);
    }

    public void delete(Long id) {
        logger.info("Deleting category with ID: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Categoria with ID " + id + " not found"));

        List<EmailDTO> emails = emailService.findByCategoriaId(id);
        if (!emails.isEmpty()) {
            logger.error("Cannot delete category with ID: {} because it has associated emails", id);
            throw new EntityInUseException("Cannot delete category with ID: " + id + " because it has associated emails");
        }
        categoriaRepository.delete(categoria);
        logger.info("Category with ID {} deleted successfully", id);
    }

    public CategoriaDTO update(Long id, CategoriaDTO categoriaDTO) {
        logger.info("Updating category with ID: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria with ID " + id + " not found"));
        categoria.setNome(categoriaDTO.getNome());
        Categoria updatedCategoria = categoriaRepository.save(categoria);
        logger.info("Category updated successfully: {}", updatedCategoria);
        return categoriaMapper.toDTO(updatedCategoria);
    }

    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        return categoriaMapper.toEntity(categoriaDTO);
    }
}