//🔹 O que é @MappedSuperclass? @MappedSuperclass indica que uma classe NÃO é uma entidade, mas fornece mapeamento JPA para suas subclasses.
package com.strart.model;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractBory extends AbstractEntity {

    private String titulo;
    private String descricao;
    private String imagem;
    @Enumerated(EnumType.STRING)
    private TipoBory tipo = TipoBory.START;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Corpo> corpos;

    public TipoBory getTipo() {	return tipo;}
	public void setTipo(TipoBory tipo) {this.tipo = tipo;}
	public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
    public List<Corpo> getCorpos() { return corpos; }
    public void setCorpos(List<Corpo> corpos) { this.corpos = corpos; }
}
