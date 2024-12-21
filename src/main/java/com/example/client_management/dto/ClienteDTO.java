package com.example.client_management.dto;

import com.example.client_management.enums.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private Long id;
    private String inscricao;
    private String nome;
    private String apelido;
    private Status status;
}
