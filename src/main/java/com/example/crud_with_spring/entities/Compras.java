package com.example.crud_with_spring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Compras implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private Integer quantidade;
    private Double valor;

    public Compras(Pessoa pessoa, Produto produto, Integer quantidade) {
        this.pessoa = pessoa;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = quantidade * produto.getValor();
    }
}
