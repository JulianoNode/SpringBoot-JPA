package com.strart.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "copos")
public class Corpo extends AbstractEntity_Id{
	
	private String nome;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
		name = "copos_tem_bory", 
        joinColumns = { @JoinColumn(name = "corpo_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "bory_id", referencedColumnName = "id") }
	)
	private List<Bory> bories;

	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
    public List<Bory> getBories() { return bories; }
    public void setBories(List<Bory> bories) { this.bories = bories; }
	
}
