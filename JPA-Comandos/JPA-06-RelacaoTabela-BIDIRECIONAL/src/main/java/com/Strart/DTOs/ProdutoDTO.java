package com.Strart.DTOs;

public class ProdutoDTO {
	private Long id;
	private String nome;
	private Double preco;
	private String imagem; // caminho ou nome do arquivo
	private Long categoriaId;
	private Long fornecedorId;
	
	// getters e setters
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public Double getPreco() {return preco;}
	public void setPreco(Double preco) {this.preco = preco;}
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
	public Long getCategoriaId() {return categoriaId;}
	public void setCategoriaId(Long categoriaId) {this.categoriaId = categoriaId;}
	public Long getFornecedorId() {return fornecedorId;}
	public void setFornecedorId(Long fornecedorId) {this.fornecedorId = fornecedorId;}	
}