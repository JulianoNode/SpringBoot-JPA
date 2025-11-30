package com.JPA_Start.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import com.JPA_Start.model.Produto;
import com.JPA_Start.repositpry.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	// ---------------------------------------------------------
	// LISTAGEM BÁSICA
	// ---------------------------------------------------------

	// Lista todos sem paginação
	public List<Produto> listarTodos() {
		return repository.findAll();
	}

	// Lista paginado simples
	public Page<Produto> listarPaginado(Pageable pageable) {
		return repository.findAll(pageable);
	}

	// Busca por nome com paginação (LIKE %nome%)
	public Page<Produto> buscarPorNome(String nome, Pageable pageable) {
		if (nome == null || nome.isBlank()) {
			return repository.findAll(pageable);
		}
		return repository.findByNomeContainingIgnoreCase(nome, pageable);
	}

	// ---------------------------------------------------------
	// CRUD COMPLETO
	// ---------------------------------------------------------

	public Produto buscarPorId(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}

	public void deletar(Long id) {
		repository.deleteById(id);
	}

	public Produto atualizar(Long id, Produto novo) {
		return repository.findById(id).map(existente -> {
			existente.setNome(novo.getNome());
			existente.setPreco(novo.getPreco());
			return repository.save(existente);
		}).orElse(null);
	}
	// ---------------------------------------------------------
	// 🔍 BUSCA AVANÇADA (filtros + ordenação)
	// ---------------------------------------------------------

	public Page<Produto> buscaAvancada(String nome, Double min, Double max, String sort, String dir, int page) {

		// 1. Ordenação dinâmica
		Sort ordenacao = Sort.unsorted();

		if (sort != null && !sort.isBlank()) {
			if ("desc".equalsIgnoreCase(dir)) {
				ordenacao = Sort.by(sort).descending();
			} else {
				ordenacao = Sort.by(sort).ascending();
			}
		}

		Pageable pageable = PageRequest.of(page, 3, ordenacao);

		// 2. Busca via repository
		return repository.buscaAvancada(nome, min, max, pageable);
	}

}