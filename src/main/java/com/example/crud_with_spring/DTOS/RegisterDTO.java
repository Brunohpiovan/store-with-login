package com.example.crud_with_spring.DTOS;

import com.example.crud_with_spring.entities.enums.UserRoles;

public record RegisterDTO(String nome, String email, String senha, UserRoles role) {
}
