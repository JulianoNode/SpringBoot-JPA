package com.strart.web.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/","index"})
public class IndexStart {
    @GetMapping()
    public String index(Model model) {
    	 model.addAttribute("mensagem", "Bem-vindo ao sistema");
        return "index"; 
    }
}
