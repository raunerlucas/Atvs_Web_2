package com.web.atvfirst.model.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Produto {
    private int id;
    private String descricao;
    private Double valor;
}
