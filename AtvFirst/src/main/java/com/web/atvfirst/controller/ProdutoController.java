package com.web.atvfirst.controller;


import com.web.atvfirst.model.DAO.ProdutoDAO;
import com.web.atvfirst.model.Entity.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    ProdutoDAO dao;

    public ProdutoController() {this.dao = new ProdutoDAO();}

    @GetMapping("/form")
    public String form(Produto produto){
        return "/produto/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("produtos", dao.buscarProdutos());
        return new ModelAndView("produto/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("produto", dao.buscarProduto(id));
        return new ModelAndView("produto/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        dao.update(produto);
        return new ModelAndView("redirect:/produto/list");
    }
    @PostMapping("/save")
    public ModelAndView save(Produto produto){
        dao.save(produto);
        return new ModelAndView("redirect:/produto/list");
    }
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") int id){
        dao.remove(id);
        return new ModelAndView("redirect:/produto/list");
    }

}
