package com.JPA_Start.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JPA_Start.model.Categoria;
import com.JPA_Start.repositpry.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	public Categoria findById(Long id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElse(null);
	}
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	public void delete(Long id) {
		categoriaRepository.deleteById(id);
	}
}