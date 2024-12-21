package com.example.client_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO {
    private Long id;
    private String email;
    private Long clienteId;
    private Long categoriaId;
}