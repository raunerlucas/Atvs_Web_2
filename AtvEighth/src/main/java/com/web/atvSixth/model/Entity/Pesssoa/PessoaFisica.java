package com.web.atvSixth.model.Entity.Pesssoa;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Entity
@ToString(callSuper = true)
public class PessoaFisica extends Pessoa implements Serializable {

    @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$",
            message = "CPF deve estar no formato 000.000.000-00 ou 00000000000")
    private String cpf;
}
