package com.Strart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Strart.model.Produto;
import com.Strart.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
}
