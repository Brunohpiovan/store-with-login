package com.example.crud_with_spring.service;

import com.example.crud_with_spring.entities.Produto;
import com.example.crud_with_spring.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public Produto findById(Integer id){
        Optional<Produto> obj = repository.findById(id);
        return obj.orElseThrow(RuntimeException::new);
    }

    public Produto create(Produto Produto){
        return repository.save(Produto);
    }

    public Produto update(Integer id,Produto Produto){
        try{
            Produto obj = findById(id);
            return repository.save(obj);
        }catch (Exception e){
                throw new RuntimeException();
        }
    }

    public void delete(Integer id){
        Produto Produto = findById(id);
        repository.delete(Produto);
    }
}
