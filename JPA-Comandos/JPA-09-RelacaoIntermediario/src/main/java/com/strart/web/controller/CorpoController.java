package com.strart.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.strart.repository.CorpoRepository;

@Controller
@RequestMapping("/corpo")
public class CorpoController {

    private final CorpoRepository repository;

    public CorpoController(CorpoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("corpos", repository.findAll());
        return "corpo/lista";
    }
}

