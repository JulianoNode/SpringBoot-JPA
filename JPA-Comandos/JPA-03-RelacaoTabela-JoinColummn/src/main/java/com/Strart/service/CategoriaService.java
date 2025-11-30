package com.Strart.service;

import java.util.List;

import com.Strart.model.Categoria;

public interface CategoriaService {
	List<Categoria> listarTodas();

	Categoria buscarPorId(Long id);

	Categoria salvar(Categoria categoria);

	void deletar(Long id);

}
