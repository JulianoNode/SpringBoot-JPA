package com.strart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.strart.model.Bory;
import com.strart.model.TipoBory;
import com.strart.repository.BoryRepository;

@Service
public class BoryService {

    private final BoryRepository repository;

    public BoryService(BoryRepository repository) {
        this.repository = repository;
    }

    public List<Bory> listarTodos() {
        return repository.findAll();
    }

    public List<Bory> listarPorTipo(TipoBory tipo) {
        return repository.findByTipo(tipo);
    }

    public void salvar(Bory bory) {
        repository.save(bory);
    }
}

