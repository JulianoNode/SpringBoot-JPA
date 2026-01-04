package com.strart.web.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.strart.service.TemaGlobalCSSService;

@Controller
public class TemaGlobalCSSController {

    private final TemaGlobalCSSService temaCorCSSService;

    public TemaGlobalCSSController(TemaGlobalCSSService temaCorCSSService) {
        this.temaCorCSSService = temaCorCSSService;
    }

    @GetMapping(value = "/tema.css", produces = "text/css")
    @ResponseBody
    public String temaCss() {

        var tema = temaCorCSSService.temaAtivo();

        return """
            :root {
                --cor-tema: %s;
                --gradiente-botao: %s;
            }

            body {
                background: var(--cor-tema);
            }

            .btn {
                background: var(--gradiente-botao);
                color: #fff;
            }
            """.formatted(
                tema.getCorTema(),
                tema.getGradienteBotao()
            );
    }
}
