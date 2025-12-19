package com.Strart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double preco;
    private String imagem; // caminho ou nome do arquivo

    // LADO DONO DO RELACIONAMENTO
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    // LADO DONO DO RELACIONAMENTO
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    // getters e setters
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public Double getPreco() {return preco;}
	public void setPreco(Double preco) {this.preco = preco;}
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
	public Categoria getCategoria() {return categoria;}
	public void setCategoria(Categoria categoria) {this.categoria = categoria;}
	public Fornecedor getFornecedor() {return fornecedor;}
	public void setFornecedor(Fornecedor fornecedor) {this.fornecedor = fornecedor;}   
}

