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
@Table(name = "middlebory")
public class MiddleBory extends AbstractBory {

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "middleborys_tem_corpo",
        joinColumns = @JoinColumn(name = "middlebory_id"),
        inverseJoinColumns = @JoinColumn(name = "corpo_id")
    )
    public List<Corpo> getCorpos() {
        return super.getCorpos();
    }
}
