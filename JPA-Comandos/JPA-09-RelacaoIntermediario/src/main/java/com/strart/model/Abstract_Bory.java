package com.strart.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Abstract_Bory  extends AbstractEntity_Conteudo{

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoBory tipo = TipoBory.START;

    @ManyToMany(mappedBy = "bories")
    private List<Corpo> corpos;

    public TipoBory getTipo() { return tipo; }
    public void setTipo(TipoBory tipo) { this.tipo = tipo; }

    public List<Corpo> getCorpos() { return corpos; }
    public void setCorpos(List<Corpo> corpos) { this.corpos = corpos; }
}
