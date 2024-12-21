package com.example.client_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO {
    private Long id;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email format is invalid")
    private String email;

    @NotNull(message = "Cliente ID cannot be null")
    private Long clienteId;

    @NotNull(message = "Categoria ID cannot be null")
    private Long categoriaId;
}