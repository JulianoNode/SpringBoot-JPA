package com.strart.modelAbstract;

import jakarta.persistence.*;
@MappedSuperclass
public abstract class AbstractEntity_Nome {

    @Column(nullable = false, unique = true)
    private String nome; // START, MIDDLE, END...

    public String getNome() { return nome;}
    public void setNome(String nome) {this.nome = nome;}

}

