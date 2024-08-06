package com.dawes.modelo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="ingredientes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	
	@ManyToMany(cascade = { CascadeType.MERGE },  fetch =FetchType.EAGER)
    @JoinTable(
        name = "platos_ingredientes", 
        joinColumns = { @JoinColumn(name = "id_ingrediente") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_plato") }
    )
	@JsonIgnore
	
	@ToString.Exclude
	
	
	private Set<Plato> platos;
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ingrediente other = (Ingrediente) obj;
        return id == other.id;
    }
    
    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}



//Excluding Circular References: By using @ToString.Exclude, you prevent Lombok from including 
//these fields in the generated toString method, thus avoiding infinite recursion.
//Custom toString Method: Implementing a custom toString method ensures that only relevant 
//fields are included, avoiding circular dependencies while providing meaningful information about the object.
//Equality Check: The equals method compares objects based on their unique identifier (id). 
//This provides a clear and simple way to check if two instances are equal.
//Avoid Recursive Calls: By not involving other entity fields in hashCode and equals, 
//you prevent recursive calls that lead to a StackOverflowError.
