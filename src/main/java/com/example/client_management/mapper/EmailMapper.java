package com.example.client_management.mapper;

import com.example.client_management.dto.EmailDTO;
import com.example.client_management.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "categoriaId", source = "categoria.id")
    EmailDTO toDTO(Email email);

    @Mapping(target = "cliente.id", source = "clienteId")
    @Mapping(target = "categoria.id", source = "categoriaId")
    Email toEntity(EmailDTO emailDTO);
}