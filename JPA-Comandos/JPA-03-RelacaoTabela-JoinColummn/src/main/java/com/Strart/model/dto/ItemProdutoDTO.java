package com.Strart.model.dto;


public class ItemProdutoDTO {

    private Long id;
    private String descricao;
    private Long produtoId;

    public ItemProdutoDTO() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id;}
    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
    public Long getProdutoId() {return produtoId;}
    public void setProdutoId(Long produtoId) {this.produtoId = produtoId;}
}