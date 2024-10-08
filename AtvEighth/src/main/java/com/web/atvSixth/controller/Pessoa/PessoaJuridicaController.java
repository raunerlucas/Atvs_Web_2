package com.web.atvSixth.controller.Pessoa;

import com.web.atvSixth.configs.Resolve;
import com.web.atvSixth.model.Entity.Pesssoa.Pessoa;
import com.web.atvSixth.model.Entity.Pesssoa.PessoaJuridica;
import com.web.atvSixth.model.Entity.Venda;
import com.web.atvSixth.model.Repository.Pessoa.PessoaJuridicaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("pessoajuridica")
public class PessoaJuridicaController {

    @Autowired
    PessoaJuridicaRepository ry;

    @Autowired
    Resolve r;

    @GetMapping("/form")
    public ModelAndView form(ModelMap model, PessoaJuridica pessoaJ) {
        model.addAttribute("pessoa", pessoaJ == null ? new PessoaJuridica() : pessoaJ);
        return new ModelAndView("Pessoa/form");
    }

    @GetMapping("/{id}")
    public ModelAndView venda(@PathVariable("id") Long idR,
                              @RequestParam(required = false) String data,
                              ModelMap model, HttpSession session) {
        List<Venda> vendas;
        var pLog = (Pessoa) session.getAttribute("pessoaLogada");
        Long id;
        if(pLog != null && pLog.getId().equals(idR))
            id = idR;
        else if (pLog != null) {
            return new ModelAndView("redirect:/pages/home");
        } else
            id = idR;
        model.addAttribute("pessoa", ry.pessoaJuridica(id));
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
    public ModelAndView list(ModelMap model, HttpServletRequest request) {
        String nameParam = request.getParameter("name");
        List<PessoaJuridica> pessJus = new ArrayList<>();
        if (nameParam != null && !nameParam.isEmpty()) {
            pessJus = ry.pessoasJuridicasByName(nameParam);
            if (!pessJus.isEmpty()) {
                model.addAttribute("findPepl", pessJus.size());
            } else {
                model.addAttribute("mgs", "Ninguém encontrado");
            }
        }
        if (pessJus.isEmpty())
            pessJus = ry.pessoasJuridicas();
        model.addAttribute("pessoa", pessJus);
        return new ModelAndView("Pessoa/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", ry.pessoaJuridica(id));
        return new ModelAndView("Pessoa/form", model);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa") @Valid PessoaJuridica pessoa, BindingResult bindingResult, ModelMap m, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) return form(m, pessoa);
        r.usuario(pessoa);
        ry.save(pessoa);
        attributes.addFlashAttribute("msg", "Bem vindo "+pessoa.getUsuario().getLogin()+"!");
        return new ModelAndView("redirect:/pages/login");
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("pessoa") @Valid PessoaJuridica pessoa, BindingResult bindingResult, ModelMap m) {
        if (bindingResult.hasErrors()
                && !bindingResult.hasFieldErrors("usuario.login")
                && !bindingResult.hasFieldErrors("usuario.password"))
            return form(m, pessoa);
        ry.update(pessoa);
        return new ModelAndView("redirect:/pessoajuridica/" + pessoa.getId());
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        ry.remove(id);
        return new ModelAndView("redirect:/pessoajuridica/list");
    }
}
