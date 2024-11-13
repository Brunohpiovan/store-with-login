package com.example.crud_with_spring.repositories;

import com.example.crud_with_spring.entities.Compras;
import com.example.crud_with_spring.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprasRepository extends JpaRepository<Compras,Integer> {
}
