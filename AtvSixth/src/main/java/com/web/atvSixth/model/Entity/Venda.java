package com.web.atvSixth.model.Entity;

import com.web.atvSixth.model.Entity.Pesssoa.Pessoa;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Scope("session")
@Entity
@Data
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itensVenda = new ArrayList<>();
    @ManyToOne
    private Pessoa pessoa;

    public void addItemVenda(ItemVenda itemVenda) {
        itensVenda.add(itemVenda);
        itemVenda.setVenda(this);
    }

    public void removeItemVenda(ItemVenda itemVenda) {
        itensVenda.remove(itemVenda);
        itemVenda.setVenda(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venda venda)) return false;
        return Objects.equals(getId(), venda.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public Double total() {
        var total = itensVenda.stream().mapToDouble(itv -> itv.total().doubleValue()).sum();
        return (double) Math.round(total * 100.0) / 100.0;
    }

    public String formtData() {
        return data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
