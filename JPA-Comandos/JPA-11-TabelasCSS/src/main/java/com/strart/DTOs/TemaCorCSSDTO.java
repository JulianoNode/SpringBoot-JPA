package com.strart.DTOs;

public class TemaCorCSSDTO {

    private String nome;
    private String corTema;
    private String gradienteBotao;

    public TemaCorCSSDTO() {}

    public TemaCorCSSDTO(String nome, String corTema, String gradienteBotao) {
        this.nome = nome;
        this.corTema = corTema;
        this.gradienteBotao = gradienteBotao;
    }

    public String getNome() {return nome;}
    public String getCorTema() {return corTema;}
    public String getGradienteBotao() {return gradienteBotao;}
}
