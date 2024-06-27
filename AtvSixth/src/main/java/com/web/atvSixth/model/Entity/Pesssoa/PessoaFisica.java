package com.web.atvSixth.model.Entity.Pesssoa;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class PessoaFisica extends Pessoa implements Serializable {
    @NotBlank
    @Size(min = 11)
    @Pattern(regexp = "\\d{11}")
    private String cpf;
}
