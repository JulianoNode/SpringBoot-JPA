package com.JPA_Start.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.JPA_Start.model.Produto;
import com.JPA_Start.repositpry.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository repository;

	public ProdutoService(ProdutoRepository repository) {
		this.repository = repository;
	}

	// findAll() Busca todos os registros de uma tabela.
	public List<Produto> listarTodos() {
		return repository.findAll();
	}

	// findById() Busca um registro pelo ID.
	public Produto buscarPorId(Long id) {
		return repository.findById(id).orElse(null);
	}

	// save() Salva um objeto no banco de dados.
	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}

	// deleteById() Deleta um objeto passado como argumento.
	public void deletar(Long id) {
		repository.deleteById(id);
	}

	// Atualiza um a tabela
	public Produto atualizar(Long id, Produto novo) {
		return repository.findById(id).map(existente -> {
			existente.setNome(novo.getNome());
			existente.setPreco(novo.getPreco());
			return repository.save(existente);
		}).orElse(null); // ou orElseThrow(() -> new ResourceNotFoundException(...))
	}

}