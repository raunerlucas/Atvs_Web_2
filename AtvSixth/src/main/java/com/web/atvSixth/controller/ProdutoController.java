package com.web.atvSixth.controller;

import com.web.atvSixth.model.Repository.ProdutoRepository;
import com.web.atvSixth.model.Entity.Produto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping("/form")
    public ModelAndView form(Produto produto) {
        return new ModelAndView("/produto/form");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("produtos", repository.produtos());
        return new ModelAndView("produto/list");
    }

    @GetMapping("/comprar")
    public ModelAndView listaCompraveis(ModelMap model) {
        model.addAttribute("produtos", repository.produtos());
        return new ModelAndView("venda/listProdVendas");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produto", repository.produto(id));
        return new ModelAndView("produto/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Produto produto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return form(produto);

        repository.update(produto);
        return new ModelAndView("redirect:/produto/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Produto produto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return form(produto);

        repository.save(produto);
        return new ModelAndView("redirect:/produto/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/produto/list");
    }

}
