package com.example.client_management.mapper;

import com.example.client_management.dto.ClienteDTO;
import com.example.client_management.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);
}