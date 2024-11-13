package com.example.crud_with_spring.service;

import com.example.crud_with_spring.DTOS.ComprasDTO;
import com.example.crud_with_spring.entities.Compras;
import com.example.crud_with_spring.entities.Pessoa;
import com.example.crud_with_spring.entities.Produto;
import com.example.crud_with_spring.repositories.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComprasService {

    @Autowired
    private ComprasRepository repository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ProdutoService produtoService;

    public List<Compras> findAll(){
        return repository.findAll();
    }

    public Compras findById(Integer id){
        Optional<Compras> obj = repository.findById(id);
        return obj.orElseThrow(RuntimeException::new);
    }

    public Compras create(ComprasDTO comprasdto){
        return repository.save(newCompras(comprasdto));
    }

    public Compras newCompras(ComprasDTO obj){
        Pessoa pessoa = pessoaService.findById(obj.getPessoa());
        Produto produto = produtoService.findById(obj.getProduto());


        Compras compras = new Compras();
        compras.setPessoa(pessoa);
        compras.setProduto(produto);
        compras.setQuantidade(obj.getQuantidade());
        compras.setValor(obj.getQuantidade() * produto.getValor());
        return compras;
    }

    public Compras update(Integer id,Compras compras){
        try{
            Compras obj = findById(id);
            return repository.save(obj);
        }catch (Exception e){
                throw new RuntimeException();
        }
    }

    public void delete(Integer id){
        Compras compras = findById(id);
        repository.delete(compras);
    }
}
