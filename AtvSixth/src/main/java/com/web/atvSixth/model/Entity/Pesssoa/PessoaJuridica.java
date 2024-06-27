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
    private String cnpj;

}
