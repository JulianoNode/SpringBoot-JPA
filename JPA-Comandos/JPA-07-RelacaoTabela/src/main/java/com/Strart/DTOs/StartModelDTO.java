package com.Strart.DTOs;

public class StartModelDTO {
	 private Long id;

	    private String titulo;     // 🔹 NOVO
	    private String descricao;
	    private String imagem;     // 🔹 NOVO (nome do arquivo)

	    private Long produtoId;
	    
	    public StartModelDTO() {}

	    // getters e setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getTitulo() { return titulo; }
	    public void setTitulo(String titulo) { this.titulo = titulo; }
	    public String getDescricao() { return descricao; }
	    public void setDescricao(String descricao) { this.descricao = descricao; }
	    public String getImagem() { return imagem; }
	    public void setImagem(String imagem) { this.imagem = imagem; }
	    public Long getProdutoId() { return produtoId; }
	    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }
	}