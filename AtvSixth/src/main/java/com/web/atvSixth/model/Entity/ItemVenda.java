package com.web.atvSixth.model.Entity;

import jakarta.persistence.*;
import lombok.Data;

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

    @Override
    public String toString() {
        return "ItemVenda{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                ", id=" + id +
                '}';
    }

    public BigDecimal total(){
        return BigDecimal.valueOf(produto.getValor() * quantidade);
    }
}
