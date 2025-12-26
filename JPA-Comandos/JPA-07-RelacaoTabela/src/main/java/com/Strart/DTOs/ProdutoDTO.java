package com.Strart.DTOs;

import java.util.List;

public class ProdutoDTO {

    private Long id;
    private String nome;

    // IDs recebidos do formulário
    private List<Long> fornecedorIds;
    private List<Long> categoriaIds;

	// getters e setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Long> getFornecedorIds() {
		return fornecedorIds;
	}
	public void setFornecedorIds(List<Long> fornecedorIds) {
		this.fornecedorIds = fornecedorIds;
	}
	public List<Long> getCategoriaIds() {
		return categoriaIds;
	}
	public void setCategoriaIds(List<Long> categoriaIds) {
		this.categoriaIds = categoriaIds;
	}

}
