package com.Strart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Strart.DTOs.ProdutoDTO;
import com.Strart.model.Categoria;
import com.Strart.model.Fornecedor;
import com.Strart.model.Produto;
import com.Strart.repository.CategoriaRepository;
import com.Strart.repository.FornecedorRepository;
import com.Strart.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	public Produto salvar(ProdutoDTO dto) {

		Produto produto = new Produto();
		produto.setNome(dto.getNome());

		// Buscar fornecedores pelos IDs
		List<Fornecedor> fornecedores = fornecedorRepository.findAllById(dto.getFornecedorIds());

		// Buscar categorias pelos IDs
		List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriaIds());

		produto.setFornecedores(fornecedores);
		produto.setCategorias(categorias);

		return produtoRepository.save(produto);
	}
}
