package com.example.crud_with_spring.controllers;


import com.example.crud_with_spring.DTOS.ComprasDTO;
import com.example.crud_with_spring.entities.Compras;
import com.example.crud_with_spring.service.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    private ComprasService service;

    @GetMapping
    public ResponseEntity<List<Compras>> findAll(){
        List<Compras> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Compras> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Compras> create(@RequestBody ComprasDTO comprasdto){
        Compras compras = service.create(comprasdto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(compras.getId()).toUri();
        return ResponseEntity.created(uri).body(compras);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Compras> update(@PathVariable Integer id,@RequestBody Compras compras){
        compras = service.update(id,compras);
        return ResponseEntity.ok().body(compras);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
