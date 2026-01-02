//🔹 O que é @MappedSuperclass? @MappedSuperclass indica que uma classe NÃO é uma entidade, mas fornece mapeamento JPA para suas subclasses.
package com.strart.model;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity_Conteudo extends AbstractEntity_Imagem {
	private static final long serialVersionUID = 1L;

	private String titulo;
    private String descricao;

	public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

}
