package com.strart.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity_Imagem extends AbstractEntity_Id{

	private static final long serialVersionUID = 1L;
	@Column(name = "imagem")
    private String imagem;
    
    public String getImagem() {
        return imagem;
    }
    
    public void setImagem(String imagem) {this.imagem = imagem;}
    public boolean hasImagem() { return imagem != null && !imagem.isBlank();}
}
