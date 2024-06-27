package com.web.atvfirst.model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class ItemVenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantidade;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Venda venda;

    public BigDecimal total(){
        return BigDecimal.valueOf(produto.getValor() * quantidade);
    }
}
