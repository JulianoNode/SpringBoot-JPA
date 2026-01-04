package com.strart.service;

import org.springframework.stereotype.Service;

import com.strart.model.TemaCorCSS;
import com.strart.repository.TemaCorCSSRepository;

@Service
public class TemaCorCSSService {

    private final TemaCorCSSRepository repository;

    public TemaCorCSSService(TemaCorCSSRepository repository) {
        this.repository = repository;
    }

    public TemaCorCSS temaAtivo() {
        return repository.findByAtivoTrue()
                .orElseThrow(() -> new RuntimeException("Nenhum tema ativo encontrado"));
    }
}

