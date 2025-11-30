package com.JPA_Start.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@OneToOne(mappedBy = "categoria")
	private Produto produto;

	public Categoria() {}

	public Categoria(String nome) {this.nome = nome; }
	public Long getId() {return id; }
	public void setId(Long id) {this.id = id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public Produto getProduto() {return produto;}
	public void setProduto(Produto produto) {this.produto = produto;}
}