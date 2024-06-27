package com.web.atvSixth.model.Entity.Pesssoa;

import com.web.atvSixth.model.Entity.Venda;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa implements Serializable {
    /*  @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private Long id;*/
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{14}")
    private String telefone;

    @OneToMany(mappedBy = "pessoa")
    private List<Venda> vendas = new ArrayList<>();

    public void addVenda(Venda venda) {
        vendas.add(venda);
    }
    public void removeVenda(Venda venda) {
        vendas.remove(venda);
    }

    public boolean tipoObjeto(String objeto){
        return this.getClass().getSimpleName().equalsIgnoreCase(objeto);
    }

    public String nomeClasse(){
        return this.getClass().getSimpleName().toLowerCase();
    }
}
