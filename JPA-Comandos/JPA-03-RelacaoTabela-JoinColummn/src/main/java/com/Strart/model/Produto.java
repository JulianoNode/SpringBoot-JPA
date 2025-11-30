package com.Strart.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private BigDecimal preco;
		
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemProduto> itens = new ArrayList<>();
	
	public Produto() {}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public BigDecimal getPreco() { return preco; }
	public void setPreco(BigDecimal preco) { this.preco = preco; }
	public Categoria getCategoria() { return categoria; }
	public void setCategoria(Categoria categoria) { this.categoria = categoria; }
	public List<ItemProduto> getItens() { return itens; }
	public void setItens(List<ItemProduto> itens) { this.itens = itens; }
}