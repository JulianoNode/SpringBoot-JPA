package com.strart.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "bory")
public class Bory extends Abstract_Bory  implements Serializable{

	@Column(name = "pasta_imagem")
	private String pastaImagem;

	public String getPastaImagem() { return pastaImagem; }
	public void setPastaImagem(String pastaImagem) { this.pastaImagem = pastaImagem; }

}
