package com.strart;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeArquivo;
    private String pasta;
    private String tipo;

    // getters e setters
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNomeArquivo() {return nomeArquivo;}
	public void setNomeArquivo(String nomeArquivo) {this.nomeArquivo = nomeArquivo;}
	public String getPasta() {return pasta;}
	public void setPasta(String pasta) {this.pasta = pasta;}
	public String getTipo() {return tipo;}
	public void setTipo(String tipo) {this.tipo = tipo;}

}
