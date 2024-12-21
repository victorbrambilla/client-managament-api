package com.example.client_management.specification;

import com.example.client_management.entity.Cliente;
import com.example.client_management.enums.Status;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecification {

    public static Specification<Cliente> hasStatus(Status status) {
        return (root, query, criteriaBuilder) ->
                status == null ? null : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Cliente> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Cliente> hasInscricao(String inscricao) {
        return (root, query, criteriaBuilder) ->
                inscricao == null ? null : criteriaBuilder.equal(root.get("inscricao"), inscricao);
    }
}