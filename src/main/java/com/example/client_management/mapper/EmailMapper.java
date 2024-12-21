package com.example.client_management.mapper;

import com.example.client_management.dto.EmailDTO;
import com.example.client_management.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "categoria.id", target = "categoriaId")
    EmailDTO toDTO(Email email);

    @Mapping(source = "clienteId", target = "cliente.id")
    @Mapping(source = "categoriaId", target = "categoria.id")
    Email toEntity(EmailDTO emailDTO);
}