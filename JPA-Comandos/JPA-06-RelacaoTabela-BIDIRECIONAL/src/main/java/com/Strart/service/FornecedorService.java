package com.Strart.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Strart.DTOs.FornecedorDTO;
import com.Strart.repository.FornecedorRepository;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    @Autowired
    private ModelMapper mapper;

    public List<FornecedorDTO> listar() {
        return repository.findAll()
                .stream()
                .map(f -> mapper.map(f, FornecedorDTO.class))
                .toList();
    }
}
