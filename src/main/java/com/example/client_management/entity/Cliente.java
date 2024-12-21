package com.example.client_management.entity;

import com.example.client_management.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inscricao;
    private String nome;
    private String apelido;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Email> emails;
}
