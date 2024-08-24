package com.web.atvSixth.controller.Pessoa;


import com.web.atvSixth.configs.Resolve;
import com.web.atvSixth.model.Entity.Pesssoa.PessoaFisica;
import com.web.atvSixth.model.Entity.Venda;
import com.web.atvSixth.model.Repository.Pessoa.PessoaFisicaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Controller
@RequestMapping("pessoafisica")
public class PessoaFisicaController {

    @Autowired
    PessoaFisicaRepository ry;

    @Autowired
    Resolve r;

    @GetMapping("/form")
    public ModelAndView form(ModelMap model, PessoaFisica pessoaF) {
        model.addAttribute("pessoa", pessoaF == null ? new PessoaFisica() : pessoaF);
        return new ModelAndView("Pessoa/form");
    }

    @GetMapping("/{id}")
    public ModelAndView listById(@PathVariable("id") Long id,
                                 @RequestParam(required = false) String data,
                                 ModelMap model) {
        model.addAttribute("pessoa", ry.pessoaFisica(id));
        List<Venda> vendas;
        if (data != null && !data.isEmpty()) {
            vendas = r.listVendasByIdPessoa(LocalDate.parse(data), id);
            if (!vendas.isEmpty()) {
                model.addAttribute("findPepl", vendas.size());
            } else {
                model.addAttribute("mgs", "Nada Encontrado");
            }
        } else
            vendas = r.listVendasByIdPessoa(id);
        model.addAttribute("vendas", vendas);
        return new ModelAndView("Pessoa/detail");
    }

    @GetMapping("list")
    public ModelAndView list(ModelMap model,
                             @RequestParam(required = false) String name) {
        List<PessoaFisica> pessFis = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            pessFis = ry.pessoasFisicasByName(name);
            if (!pessFis.isEmpty()) {
                model.addAttribute("findPepl", pessFis.size());
            } else {
                model.addAttribute("mgs", "Ninguém encontrado");
            }
        }
        if (pessFis.isEmpty())
            pessFis = ry.pessoasFisicas();
        model.addAttribute("pessoa", pessFis);
        return new ModelAndView("Pessoa/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        PessoaFisica pessoaFisica = ry.pessoaFisica(id);
        model.addAttribute("pessoa", pessoaFisica);
        return new ModelAndView("Pessoa/form", model);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa") @Valid PessoaFisica pessoa,
                             BindingResult bindingResult,
                             ModelMap m, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) return form(m, pessoa);
        r.usuario(pessoa);
        ry.save(pessoa);
        attributes.addFlashAttribute("msg", "Bem vindo "+pessoa.getUsuario().getLogin()+"!");
        return new ModelAndView("redirect:/pages/login");
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("pessoa") @Valid PessoaFisica pessoaFisica,
                               BindingResult bindingResult, ModelMap m) {
        if (bindingResult.hasErrors()
                && !bindingResult.hasFieldErrors("usuario.login")
                && !bindingResult.hasFieldErrors("usuario.password"))
            return form(m, pessoaFisica);
        ry.update(pessoaFisica);
        return new ModelAndView("redirect:/pessoafisica/" + pessoaFisica.getId());
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        ry.remove(id);
        return new ModelAndView("redirect:/pessoafisica/list");
    }

}
