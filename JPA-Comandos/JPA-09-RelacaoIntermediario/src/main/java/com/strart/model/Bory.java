package com.strart.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "bory")
public class Bory extends AbstractBory {

    @ManyToMany(mappedBy = "bories")
    private List<Corpo> corpos;

}
