package com.strart.modelAbstract;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity_Id implements Serializable {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    public Long getId() { return id; }

	    public boolean hasId() { return id != null; }
	    public boolean hasNotId() { return id == null; }

	    @Override
	    public int hashCode() {
	        return (id == null) ? 0 : id.hashCode();
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        AbstractEntity_Id other = (AbstractEntity_Id) obj;
	        return id != null && id.equals(other.id);
	    }
}
