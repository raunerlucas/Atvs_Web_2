package com.web.atvfirst.controller.Pessoa;

import com.web.atvfirst.model.Entity.Pesssoa.PessoaJuridica;
import com.web.atvfirst.model.Repository.Pessoa.PessoaJuridicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("pessoajuridica")
public class PessoaJuridicaController {

    @Autowired
    PessoaJuridicaRepository ry;

    @GetMapping("/form")
    public ModelAndView form(ModelMap model, PessoaJuridica pessoaJ){
        model.addAttribute("pessoa", pessoaJ == null ? new PessoaJuridica() : pessoaJ);
        return new ModelAndView("Pessoa/form");
    }

    @GetMapping("/{id}")
    public ModelAndView venda(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("pessoa", ry.pessoaJuridica(id));
        return new ModelAndView("Pessoa/detail");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", ry.pessoaJuridica(id));
        return new ModelAndView("Pessoa/form", model);
    }

    @GetMapping("list")
    public ModelAndView venda(ModelMap model){
        model.addAttribute("pessoa", ry.pessoasJuridicas());
        return new ModelAndView("Pessoa/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa") PessoaJuridica pessoa){
        ry.save(pessoa);
        return new ModelAndView("redirect:/pessoajuridica/list");
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("pessoa") PessoaJuridica pessoaJuridica) {
        ry.update(pessoaJuridica);
        return new ModelAndView("redirect:/pessoajuridica/"+pessoaJuridica.getId());
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        ry.remove(id);
        return new ModelAndView("redirect:/pessoajuridica/list");
    }
}
