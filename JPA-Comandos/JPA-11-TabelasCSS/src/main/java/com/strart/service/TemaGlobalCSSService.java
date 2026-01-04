package com.strart.service;

import org.springframework.stereotype.Service;

import com.strart.DTOs.TemaCorCSSDTO;
import com.strart.repository.TemaCorCSSRepository;

@Service
public class TemaGlobalCSSService {

    private final TemaCorCSSRepository repository;

    public TemaGlobalCSSService(TemaCorCSSRepository repository) {
        this.repository = repository;
    }

    public TemaCorCSSDTO temaAtivo() {

        return repository.findByAtivoTrue()
            .map(tema -> new TemaCorCSSDTO(
                tema.getNome(),
                tema.getCorTema(),
                tema.getGradienteBotao()
            ))
            .orElseGet(this::temaPadrao);
    }

    private TemaCorCSSDTO temaPadrao() {
        return new TemaCorCSSDTO(
            "Padrão",
            "#2c3e50",
            "linear-gradient(135deg, #4ca1af, #2c3e50)"
        );
    }
    
}
