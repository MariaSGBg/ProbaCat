package com.dawes.modelo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="platos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Plato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique=true)
	private String nombre;
	private double precio;
	private String imagen;
	
	@ManyToOne
	@JoinColumn(name="id_categoria", nullable=true)
	@JsonIgnore
	
	@ToString.Exclude
	
	
	private Categoria categoria;
	
	@ManyToMany(mappedBy = "platos", fetch = FetchType.EAGER)
	@JsonIgnore	
	
	@ToString.Exclude
	
	
	private Set<Ingrediente> ingredientes;
	
	@OneToMany(mappedBy="plato")
	
	@ToString.Exclude
	
	
	private Set<MenuPlato> menus;
	
	// I
//	 @Override
//	    public int hashCode() {
//	        int hash = 7;
//	        hash = 31 * hash + (id == null ? 0 : id.hashCode());
//	        return hash;
//	    }
	 
	
	// II
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + (id == null ? 0 : id.hashCode());
	        result = prime * result + (nombre == null ? 0 : nombre.hashCode());
	        return result;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        Plato other = (Plato) obj;
	        return id != null && id.equals(other.id);
	    }
	    
	    @Override
	    public String toString() {
	        return "Plato{" +
	                "id=" + id +
	                ", nombre='" + nombre + '\'' +
	                ", precio=" + precio +
	                ", imagen='" + imagen + '\'' +
	                '}';
	    }
}
