package com.JPA_Start.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.JPA_Start.model.Produto;
import com.JPA_Start.repositpry.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

	// findAll() Busca todos os registros de uma tabela.
	public List<Produto> listarTodos() {
		return repository.findAll();
	}

	// Busca Isso permite procurar por qualquer parte do nome (LIKE %nome%)
	public Page<Produto> listarPaginado(Pageable pageable) {
		return repository.findAll(pageable);
	}

    public Page<Produto> buscarPorNome(String nome, Pageable pageable) {
        if (nome == null || nome.isBlank()) {
            return repository.findAll(pageable);
        }
        return repository.findByNomeContainingIgnoreCase(nome, pageable);
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