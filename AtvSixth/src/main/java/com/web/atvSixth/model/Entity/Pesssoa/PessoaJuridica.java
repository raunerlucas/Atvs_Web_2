package com.web.atvSixth.model.Entity.Pesssoa;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class PessoaJuridica extends Pessoa implements Serializable {
    @NotBlank
    private String razaoSocial;

    @Size(min = 14, max = 18)
    @Pattern(regexp = "^(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14})$",
            message = "CNPJ deve estar no formato 00.000.000/0000-00 ou 00000000000000")
    private String cnpj;
}
