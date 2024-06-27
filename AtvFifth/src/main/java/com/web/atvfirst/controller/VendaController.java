package com.web.atvfirst.controller;


import com.web.atvfirst.model.Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("venda")
public class VendaController {

    @Autowired
    VendaRepository ry;

    @GetMapping("/{id}")
    public ModelAndView venda(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("venda", ry.venda(id));
        return new ModelAndView("venda/detail");
    }

    @GetMapping("list")
    public ModelAndView venda( ModelMap model){
        model.addAttribute("vendas", ry.vendas());
        return new ModelAndView("venda/list");
    }
}
