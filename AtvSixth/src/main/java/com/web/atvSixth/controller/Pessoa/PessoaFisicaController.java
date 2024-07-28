package com.web.atvSixth.controller.Pessoa;


import com.web.atvSixth.model.Entity.Pesssoa.PessoaFisica;
import com.web.atvSixth.model.Repository.Pessoa.PessoaFisicaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static java.rmi.server.LogStream.log;


@Transactional
@Controller
@RequestMapping("pessoafisica")
public class PessoaFisicaController {

    private static final Logger log = LoggerFactory.getLogger(PessoaFisicaController.class);
    @Autowired
    PessoaFisicaRepository ry;

    @GetMapping("/form")
    public ModelAndView form(ModelMap model, PessoaFisica pessoaF) {
        model.addAttribute("pessoa", pessoaF == null ? new PessoaFisica() : pessoaF);
        return new ModelAndView("Pessoa/form");
    }

    @GetMapping("/{id}")
    public ModelAndView listById(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", ry.pessoaFisica(id));
        return new ModelAndView("Pessoa/detail");
    }

    @GetMapping("list")
    public ModelAndView list(ModelMap model, HttpServletRequest request) {
        String nameParam = request.getParameter("name");
        List<PessoaFisica> pessFis = new ArrayList<>();
        if (nameParam != null && !nameParam.isEmpty()) {
            pessFis = ry.pessoasFisicasByName(nameParam);
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
        model.addAttribute("pessoa", ry.pessoaFisica(id));
        return new ModelAndView("Pessoa/form", model);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa") @Valid PessoaFisica pessoa, BindingResult bindingResult, ModelMap m) {
        if (bindingResult.hasErrors()) return form(m, pessoa);

        ry.save(pessoa);
        return new ModelAndView("redirect:/pessoafisica/list");
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("pessoa") @Valid PessoaFisica pessoaFisica, BindingResult bindingResult, ModelMap m) {
        if (bindingResult.hasErrors()) return form(m, pessoaFisica);
        ry.update(pessoaFisica);
        return new ModelAndView("redirect:/pessoafisica/" + pessoaFisica.getId());
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        ry.remove(id);
        return new ModelAndView("redirect:/pessoafisica/list");
    }

}
