package com.web.atvSixth.model.Entity.Pesssoa;

import jakarta.persistence.Entity;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class PessoaJuridica extends Pessoa implements Serializable {
    private String razaoSocial;
    private String cnpj;

}
