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
public class Corpo extends AbstractEntity{
	
	private String nome;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
		name = "copos_tem_startbory", 
        joinColumns = { @JoinColumn(name = "corpo_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "startbory_id", referencedColumnName = "id") }
	)
	private List<StartBory> startBories;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
		name = "copos_tem_middlebory", 
        joinColumns = { @JoinColumn(name = "corpo_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "middlebory_id", referencedColumnName = "id") }
	)
	private List<MiddleBory> middleBories;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
		name = "copos_tem_endbory", 
        joinColumns = { @JoinColumn(name = "corpo_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "endbory_id", referencedColumnName = "id") }
	)
	private List<EndBory> endBories;

	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public List<StartBory> getStartBories() {return startBories;}
	public void setStartBories(List<StartBory> startBories) {this.startBories = startBories;}
	public List<MiddleBory> getMiddleBories() {	return middleBories;}
	public void setMiddleBories(List<MiddleBory> middleBories) {this.middleBories = middleBories;}
	public List<EndBory> getEndBories() {return endBories;}
	public void setEndBories(List<EndBory> endBories) {	this.endBories = endBories;}	
}
