package com.example.crud_with_spring.repositories;

import com.example.crud_with_spring.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface PessoaRepository extends JpaRepository<Pessoa,Integer> {

    UserDetails findByEmail(String email);
}
