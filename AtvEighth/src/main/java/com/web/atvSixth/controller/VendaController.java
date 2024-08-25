package com.web.atvSixth.controller;

import com.web.atvSixth.configs.Resolve;
import com.web.atvSixth.model.Entity.Pesssoa.Pessoa;
import com.web.atvSixth.model.Entity.Pesssoa.PessoaFisica;
import com.web.atvSixth.model.Entity.Venda;
import com.web.atvSixth.model.Repository.VendaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Scope("request")
@Controller
@RequestMapping("venda")
public class VendaController {

    @Autowired
    VendaRepository ry;

    @Autowired
    Venda venda;

    @Autowired
    private Resolve r;

    @GetMapping("/form")
    public String form(Model model) {
        List<? extends Pessoa> pessoas = r.getPessoas();
        model.addAttribute("pessoas", pessoas);
        model.addAttribute("pessoa", new PessoaFisica());
        return "venda/form";
    }

    @PostMapping("/save")
    public ModelAndView setCliente(PessoaFisica pessoa, HttpSession session) {
        var p = r.isPessoa(pessoa.getId());
        venda.setPessoa(p);
        venda.setData(LocalDate.now());
        ry.save(venda);
        session.removeAttribute("venda");
        return new ModelAndView("redirect:/venda/list");
    }

    @GetMapping("/produto/add/{id}")
    public String addProduto(@PathVariable("id") Long idProduto, HttpServletRequest request) {
        String cartParam = request.getParameter("cart");
        venda.setItensVenda(r.itensVenda(idProduto, venda, false));
        return cartParam != null && cartParam.equals("true") ? "redirect:/venda/form" : "redirect:/produto/comprar";
    }

    @GetMapping("/produto/retira/{id}")
    public String retiraProduto(@PathVariable("id") Long idProduto, HttpServletRequest request) {
        String cartParam = request.getParameter("cart");
        venda.setItensVenda(r.itensVenda(idProduto, venda, true));
        return cartParam != null && cartParam.equals("true") ? "redirect:/venda/form" : "redirect:/produto/comprar";
    }

    @GetMapping("/produto/remove/{id}")
    public String removeProduto(@PathVariable("id") Long idProduto, HttpServletRequest request) {
        String cartParam = request.getParameter("cart");
        venda.setItensVenda(r.removeItensVenda(idProduto, venda));
        return cartParam != null && cartParam.equals("true") ? "redirect:/venda/form" : "redirect:/produto/comprar";
    }

    @GetMapping("/{id}")
    public ModelAndView vendaById(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", ry.venda(id));
        return new ModelAndView("venda/detail");
    }

    @GetMapping("list")
    public ModelAndView vendaList(@RequestParam(required = false) String data, ModelMap model) {
        List<Venda> vendas = new ArrayList<>();
        if (data != null && !data.isEmpty()) {
            vendas = ry.vendasByData(LocalDate.parse(data));
            if (!vendas.isEmpty()) {
                model.addAttribute("findPepl", vendas.size());
            } else {
                model.addAttribute("mgs", "Nada Encontrado");
            }
        }
        if (vendas.isEmpty())
            vendas = ry.vendas();
        model.addAttribute("vendas", vendas);
        return new ModelAndView("venda/list");
    }


    @GetMapping("/comprasUser")
    public ModelAndView vendaListPessoa(@RequestParam(required = false) String data, ModelMap model, HttpSession session) {
        Long pessoaId = ((Pessoa) session.getAttribute("pessoaLogada")).getId();
        List<Venda> vendas = new ArrayList<>();
        if (data != null && !data.isEmpty()) {
            vendas = ry.vendasByDataAndPessoa(LocalDate.parse(data), pessoaId);
            if (!vendas.isEmpty()) {
                model.addAttribute("findPepl", vendas.size());
            } else {
                model.addAttribute("mgs", "Nada Encontrado");
            }
        }
        if (vendas.isEmpty())
            vendas = ry.vendasByPessoa(pessoaId);
        model.addAttribute("vendas", vendas);
        return new ModelAndView("venda/minhasCompras");
    }

}
