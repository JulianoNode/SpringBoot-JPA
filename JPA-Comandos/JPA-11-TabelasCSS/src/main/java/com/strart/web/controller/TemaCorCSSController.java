package com.strart.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.strart.model.TemaCorCSS;
import com.strart.service.TemaCorCSSService;

@Controller
@RequestMapping("/temacorCSS")
public class TemaCorCSSController {

    private final TemaCorCSSService service;

    public TemaCorCSSController(TemaCorCSSService service) {
        this.service = service;
    }

    /* =========================
       LISTAR
       ========================= */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("temas", service.listar());
        return "temacorcss/lista";
    }

    /* =========================
       FORM NOVO
       ========================= */
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tema", new TemaCorCSS());
        return "temacorcss/form";
    }

    /* =========================
       SALVAR (NOVO / EDITAR)
       ========================= */
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute TemaCorCSS tema) {
        service.salvar(tema);
        return "redirect:/temacorCSS";
    }

    /* =========================
       EDITAR
       ========================= */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("tema", service.buscarPorId(id));
        return "temacorcss/form";
    }

    /* =========================
       REMOVER
       ========================= */
    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Long id) {
        service.remover(id);
        return "redirect:/temacorCSS";
    }
}

