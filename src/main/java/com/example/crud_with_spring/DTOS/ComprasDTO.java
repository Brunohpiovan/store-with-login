package com.example.crud_with_spring.DTOS;


import com.example.crud_with_spring.entities.Compras;
import com.example.crud_with_spring.entities.Pessoa;
import com.example.crud_with_spring.entities.Produto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
public class ComprasDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer pessoa;
    private Integer produto;
    private Integer quantidade;
    private Double valor;

    public ComprasDTO() {
    }

    @JsonCreator
    public ComprasDTO(@JsonProperty("pessoa") Integer pessoa,@JsonProperty("produto") Integer produto, @JsonProperty("quantidade") int quantidade) {
        this.pessoa = pessoa;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = null;
    }


}