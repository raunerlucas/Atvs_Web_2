package com.web.atvfirst.model.Entity.Pesssoa;

import com.web.atvfirst.model.Entity.Venda;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

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
    private String nome;
    private String telefone;
    @OneToMany(mappedBy = "pessoa")
    private List<Venda> vendas;

    public boolean tipoObjeto(String objeto){
        return this.getClass().getSimpleName().toLowerCase().equals(objeto.toLowerCase());
    }

    public String nomeClasse(){
        return this.getClass().getSimpleName().toLowerCase();
    }
}
