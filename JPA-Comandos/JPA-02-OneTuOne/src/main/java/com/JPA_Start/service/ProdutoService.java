package com.JPA_Start.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JPA_Start.model.Produto;
import com.JPA_Start.repositpry.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto findById(Long id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElse(null);
	}

	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void delete(Long id) {
		produtoRepository.deleteById(id);
	}
}