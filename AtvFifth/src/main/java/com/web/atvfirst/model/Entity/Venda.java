package com.web.atvfirst.model.Entity;

import com.web.atvfirst.model.Entity.Pesssoa.Pessoa;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itensVenda = new ArrayList<>();
    @ManyToOne
    private Pessoa pessoa;

    public Double total(){
        var total = itensVenda.stream().mapToDouble(itv -> itv.total().doubleValue()).sum();
        return (double) Math.round(total * 100.0) / 100.0;
    }

    public String formtData(){
        return data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
