package com.example.crud_with_spring.controllers;


import com.example.crud_with_spring.entities.Produto;
import com.example.crud_with_spring.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        List<Produto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto Produto){
        Produto = service.create(Produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Produto.getId()).toUri();
        return ResponseEntity.created(uri).body(Produto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> update(@PathVariable Integer id,@RequestBody Produto Produto){
        Produto = service.update(id,Produto);
        return ResponseEntity.ok().body(Produto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
