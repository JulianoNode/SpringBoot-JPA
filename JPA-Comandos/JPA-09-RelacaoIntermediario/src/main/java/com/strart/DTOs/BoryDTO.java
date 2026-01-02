package com.strart.DTOs;

import com.strart.model.TipoBory;

public class BoryDTO {

    private Long id;
    private String nome;
    private String titulo;
    private String descricao;
    private String imagem;
    private TipoBory tipo;
    
    // gettes em settes
    public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public TipoBory getTipo() {	return tipo;}
	public void setTipo(TipoBory tipo) {this.tipo = tipo;}
	public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
}
