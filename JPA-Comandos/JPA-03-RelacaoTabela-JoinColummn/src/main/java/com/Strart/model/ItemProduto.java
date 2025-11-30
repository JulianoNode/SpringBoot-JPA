package com.Strart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itemprodutos")
public class ItemProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	public ItemProduto() {}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	public Produto getProduto() { return produto; }
	public void setProduto(Produto produto) { this.produto = produto; }
}