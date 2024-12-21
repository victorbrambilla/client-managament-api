package com.example.client_management.service;

import com.example.client_management.dto.CategoriaDTO;
import com.example.client_management.entity.Categoria;
import com.example.client_management.exception.ResourceNotFoundException;
import com.example.client_management.mapper.CategoriaMapper;
import com.example.client_management.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper CategoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = CategoriaMapper;
    }

    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria with ID " + id + " not found"));
        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO create(CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        return categoriaMapper.toDTO(categoriaRepository.save(categoria));
    }

    public void delete(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Categoria with ID " + id + " not found"));
        categoriaRepository.delete(categoria);
    }

    public CategoriaDTO update(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria with ID " + id + " not found"));
        categoria.setNome(categoriaDTO.getNome());
        return categoriaMapper.toDTO(categoriaRepository.save(categoria));
    }
}