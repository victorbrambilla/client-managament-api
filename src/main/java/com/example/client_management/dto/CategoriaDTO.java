package com.example.client_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {
    private Long id;

    @NotBlank(message = "Nome cannot be blank")
    @Size(max = 100, message = "Nome must not exceed 100 characters")
    private String nome;
}
