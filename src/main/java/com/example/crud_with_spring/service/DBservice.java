package com.example.crud_with_spring.service;

import com.example.crud_with_spring.entities.Pessoa;
import com.example.crud_with_spring.entities.Produto;
import com.example.crud_with_spring.repositories.PessoaRepository;
import com.example.crud_with_spring.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBservice {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;


    public void instanciaDB(){
       // Pessoa p1 = new Pessoa(null,"Bruno","bruno@gmail.com","1234");
       // Pessoa p2 = new Pessoa(null,"Pedro","pedro@gmail.com","1234");

        Produto prod1 = new Produto(null,"PS5",3500.5);
        Produto prod2 = new Produto(null,"iphone",5500.5);
        Produto prod3 = new Produto(null,"relogio",33000.5);
        Produto prod4 = new Produto(null,"computador",5450.5);


       // pessoaRepository.saveAll(Arrays.asList(p1,p2));
        produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3,prod4));

    }
}
