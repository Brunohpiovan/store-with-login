package com.example.crud_with_spring.controllers;


import com.example.crud_with_spring.entities.Pessoa;
import com.example.crud_with_spring.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll(){
        List<Pessoa> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa){
        pessoa = service.create(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Integer id,@RequestBody Pessoa pessoa){
        pessoa = service.update(id,pessoa);
        return ResponseEntity.ok().body(pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
