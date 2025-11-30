package com.Strart.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Strart.model.Categoria;
import com.Strart.repository.CategoriaRepository;
import com.Strart.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaRepository categoriaRepository;

	public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public List<Categoria> listarTodas() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria buscarPorId(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
	}

	@Override
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public void deletar(Long id) {
		categoriaRepository.deleteById(id);
	}
}