package com.strart.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.strart.service.TemaCorCSSService;

@ControllerAdvice
public class TemaGlobalCSSController {

    private final TemaCorCSSService service;

    public TemaGlobalCSSController(TemaCorCSSService service) {
        this.service = service;
    }

    @ModelAttribute
    public void carregarTema(Model model) {
        model.addAttribute("tema", service.temaAtivo());
    }
}
