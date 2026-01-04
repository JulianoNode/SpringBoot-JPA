package com.strart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.strart.model.TemaCorCSS;
import com.strart.repository.TemaCorCSSRepository;

@Service
public class TemaCorCSSService {

    private final TemaCorCSSRepository repository;

    public TemaCorCSSService(TemaCorCSSRepository repository) {
        this.repository = repository;
    }

    public List<TemaCorCSS> listar() {
        return repository.findAll();
    }

    public TemaCorCSS buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tema não encontrado"));
    }

    public void salvar(TemaCorCSS tema) {

        if (Boolean.TRUE.equals(tema.getAtivo())) {
            repository.findByAtivoTrue()
                .ifPresent(t -> {
                    t.setAtivo(false);
                    repository.save(t);
                });
        }

        repository.save(tema);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}
