package com.web.atvfirst.model.Entity.Pesssoa;

import jakarta.persistence.Entity;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class PessoaFisica extends Pessoa implements Serializable {
    private String cpf;
}
