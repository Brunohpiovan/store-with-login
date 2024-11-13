package com.example.crud_with_spring.service;

import com.example.crud_with_spring.entities.Pessoa;
import com.example.crud_with_spring.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> findAll(){
        return repository.findAll();
    }

    public Pessoa findById(Integer id){
        Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(RuntimeException::new);
    }

    public Pessoa create(Pessoa pessoa){
        return repository.save(pessoa);
    }

    public Pessoa update(Integer id,Pessoa pessoa){
        try{
            Pessoa obj = findById(id);
            return repository.save(obj);
        }catch (Exception e){
                throw new RuntimeException();
        }
    }

    public void delete(Integer id){
        Pessoa pessoa = findById(id);
        repository.delete(pessoa);
    }
}
