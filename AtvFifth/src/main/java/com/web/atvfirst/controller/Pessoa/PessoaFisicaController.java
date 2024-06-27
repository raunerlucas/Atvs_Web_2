package com.web.atvfirst.controller.Pessoa;


import com.web.atvfirst.model.Entity.Pesssoa.PessoaFisica;
import com.web.atvfirst.model.Repository.Pessoa.PessoaFisicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("pessoafisica")
public class PessoaFisicaController {

    @Autowired
    PessoaFisicaRepository ry;

    @GetMapping("/form")
    public ModelAndView form(ModelMap model, PessoaFisica pessoaF) {
        model.addAttribute("pessoa", pessoaF == null ? new PessoaFisica() : pessoaF);
        return new ModelAndView("Pessoa/form");
    }


    @GetMapping("/{id}")
    public ModelAndView venda(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", ry.pessoaFisica(id));
        return new ModelAndView("Pessoa/detail");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", ry.pessoaFisica(id));
        return new ModelAndView("Pessoa/form", model);
    }

    @GetMapping("list")
    public ModelAndView venda(ModelMap model) {
        model.addAttribute("pessoa", ry.pessoasFisicas());
        return new ModelAndView("Pessoa/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa") PessoaFisica pessoa) {
        ry.save(pessoa);
        return new ModelAndView("redirect:/pessoafisica/list");
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("pessoa") PessoaFisica pessoaFisica) {
        ry.update(pessoaFisica);
        return new ModelAndView("redirect:/pessoafisica/" + pessoaFisica.getId());
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        ry.remove(id);
        return new ModelAndView("redirect:/pessoafisica/list");
    }
}
