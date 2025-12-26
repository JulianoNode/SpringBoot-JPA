package com.Strart.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	/* Produto ↔ Fornecedor */
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "produtos_tem_fornecedor", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "fornecedor_id"))
	private List<Fornecedor> fornecedores = new ArrayList<>();
	/* Produto ↔ Categoria */
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "produtos_tem_categorias", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();
	/* Produto -> StartModel */
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StartModel> startmodels = new ArrayList<>();
	
	public Produto() {}	
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public List<Fornecedor> getFornecedores() {return fornecedores;}
	public void setFornecedores(List<Fornecedor> fornecedores) {this.fornecedores = fornecedores;}
	public List<Categoria> getCategorias() {return categorias;}
	public void setCategorias(List<Categoria> categorias) {this.categorias = categorias;}
	public List<StartModel> getStartmodels() {return startmodels;}
	public void setStartmodels(List<StartModel> startmodels) {this.startmodels = startmodels;
	}
}
