package com.Strart.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Strart.DTOs.CategoriaDTO;
import com.Strart.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private ModelMapper mapper;

    public List<CategoriaDTO> listar() {
        return repository.findAll()
                .stream()
                .map(c -> mapper.map(c, CategoriaDTO.class))
                .toList();
    }
}
