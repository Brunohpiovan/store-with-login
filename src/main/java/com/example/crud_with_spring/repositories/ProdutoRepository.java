package com.example.crud_with_spring.repositories;

import com.example.crud_with_spring.entities.Pessoa;
import com.example.crud_with_spring.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
}
