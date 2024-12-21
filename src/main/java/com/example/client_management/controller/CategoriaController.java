package com.example.client_management.controller;

import com.example.client_management.dto.CategoriaDTO;
import com.example.client_management.entity.Categoria;
import com.example.client_management.service.CategoriaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Categorias", description = "Gerenciamento dos recursos de Categoria")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Operation(summary = "Retorna todas as categorias")
    @GetMapping
    public List<CategoriaDTO> findAll() {
        return categoriaService.findAll();
    }

    @Operation(summary = "Retorna a categoria pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @Operation(summary = "Cria uma nova categoria")
    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategory(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.create(categoriaDTO));
    }

    @Operation(summary = "Atualiza uma categoria pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.update(id, categoriaDTO));
    }

    @Operation(summary = "Remove uma categoria pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
