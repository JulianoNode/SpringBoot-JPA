package com.strart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tema_cor_css")
public class TemaCorCSS extends AbstractEntity_Id {

    @Column(nullable = false)
    private String nome;

    // Cor principal do tema (header, background, etc.)
    @Column(name = "cor_tema", nullable = false)
    private String corTema;

    // Gradiente do botão
    @Column(name = "gradiente_inicio", nullable = false)
    private String gradienteInicio;

    @Column(name = "gradiente_fim", nullable = false)
    private String gradienteFim;

    private Boolean ativo = true;

    /* ======================
       MÉTODOS AUXILIARES
       ====================== */

    public String getGradienteBotao() {
        return String.format(
            "linear-gradient(135deg, %s, %s)",
            gradienteInicio,
            gradienteFim
        );
    }

    /* Getters e Setters */
	
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCorTema() { return corTema; }
    public void setCorTema(String corTema) { this.corTema = corTema; }
    public String getGradienteInicio() { return gradienteInicio; }
    public void setGradienteInicio(String gradienteInicio) { this.gradienteInicio = gradienteInicio; }
    public String getGradienteFim() { return gradienteFim; }
    public void setGradienteFim(String gradienteFim) { this.gradienteFim = gradienteFim; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
