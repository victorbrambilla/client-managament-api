package com.example.client_management.mapper;

import com.example.client_management.dto.CategoriaDTO;
import com.example.client_management.entity.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDTO toDTO(Categoria categoria);
    Categoria toEntity(CategoriaDTO categoriaDTO);
}