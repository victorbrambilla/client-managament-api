package com.example.client_management.dto;

import com.example.client_management.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private Long id;

    @NotBlank(message = "Inscrição cannot be blank")
    @Size(max = 50, message = "Inscrição must not exceed 50 characters")
    private String inscricao;

    @NotBlank(message = "Nome cannot be blank")
    @Size(max = 100, message = "Nome must not exceed 100 characters")
    private String nome;

    @NotBlank(message = "Apelido cannot be blank")
    @Size(max = 50, message = "Apelido must not exceed 50 characters")
    private String apelido;

    @NotNull(message = "Status cannot be null")
    private Status status;
}
