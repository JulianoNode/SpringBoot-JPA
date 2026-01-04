package com.strart.modelAbstract;

import jakarta.persistence.*;

@MappedSuperclass
public class AbstractEntity_Limite {

    @Column(nullable = false)
    private int limite;

    @Column(nullable = false)
    private int quantidadeCards = 0;

    // construtores
    public AbstractEntity_Limite() {}

    public int getLimite() {return limite;}
    public void setLimite(int limite) {this.limite = limite;}
    public int getQuantidadeCards() {return quantidadeCards;}
    public void setQuantidadeCards(int quantidadeCards) {this.quantidadeCards = quantidadeCards;
    }

}

