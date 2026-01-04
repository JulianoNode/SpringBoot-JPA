package com.strart.web.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.strart.service.TemaGlobalCSSService;

@Controller
@RequestMapping({"/", "/index"})
public class IndexStart {

    private final TemaGlobalCSSService temaCorCSSService;

    public IndexStart(TemaGlobalCSSService temaCorCSSService) {
        this.temaCorCSSService = temaCorCSSService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("mensagem", "Bem-vindo ao sistema");
        model.addAttribute("tema", temaCorCSSService.temaAtivo());
        return "index";
    }
}
