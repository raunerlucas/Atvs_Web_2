package com.web.atvSixth.configs;

import com.web.atvSixth.model.Entity.ItemVenda;
import com.web.atvSixth.model.Entity.Pesssoa.Pessoa;
import com.web.atvSixth.model.Entity.Venda;
import com.web.atvSixth.model.Repository.Pessoa.PessoaFisicaRepository;
import com.web.atvSixth.model.Repository.Pessoa.PessoaJuridicaRepository;
import com.web.atvSixth.model.Repository.ProdutoRepository;
import com.web.atvSixth.model.Repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Transactional
@Component
public class Resolve {
    @Autowired
    PessoaFisicaRepository ryPF;

    @Autowired
    PessoaJuridicaRepository ryPJ;

    @Autowired
    ProdutoRepository ryProd;

    @Autowired
    VendaRepository ryVenda;

    public Pessoa isPessoa(Long id) {
        Pessoa pessoa = ryPF.pessoaFisica(id);
        if (pessoa == null) {
            pessoa = ryPJ.pessoaJuridica(id);
        }
        return pessoa;
    }

    public List<? extends Pessoa> getPessoas() {
        List<? extends Pessoa> pessoas = ryPF.pessoasFisicas();
        pessoas.addAll((Collection) ryPJ.pessoasJuridicas());
        return pessoas;
    }

    public List<Venda> listVendasByIdPessoa(Long id) {
        return ryVenda.vendasByPessoa(id);
    }

    public List<Venda> listVendasByIdPessoa(LocalDate data, Long id) {
        return ryVenda.vendasByDataAndPessoa(data, id);
    }


    public ItemVenda newItemVenda(Long idProduto, Venda venda) {
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setProduto(ryProd.produto(idProduto));
        itemVenda.setQuantidade(1.0);
        itemVenda.setVenda(venda);
        return itemVenda;
    }

    public List<ItemVenda> itensVenda(Long idProduto, Venda venda, boolean retirar) {
        List<ItemVenda> itensVenda = venda.getItensVenda();
        boolean exite = false;
        for (ItemVenda itemVenda : itensVenda) {
            if (itemVenda.getProduto().getId().equals(idProduto)) {
                if (retirar) {
                    if (itemVenda.getQuantidade() > 1)
                        itemVenda.setQuantidade(itemVenda.getQuantidade() - 1);
                } else {
                    itemVenda.setQuantidade(itemVenda.getQuantidade() + 1);
                }
                exite = true;
            }
        }
        if (!exite) {
            venda.addItemVenda(newItemVenda(idProduto, venda));
        }
        return itensVenda;
    }

    public List<ItemVenda> removeItensVenda(Long idProduto, Venda venda) {
        List<ItemVenda> itensVenda = venda.getItensVenda();
        itensVenda.removeIf(itemVenda -> itemVenda.getProduto().getId().equals(idProduto));
        return itensVenda;
    }
}
