package com.dawes.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="menus")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	
//	@OneToMany(mappedBy="menu")
//	private Set<MenuPlato> platos;
	
	 @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<MenuPlato> platos = new ArrayList<>();
	 
	 /////
	 public void addPlato(MenuPlato menuPlato) {
        platos.add(menuPlato);
        menuPlato.setMenu(this);
    }

    public void removePlato(MenuPlato menuPlato) {
        platos.remove(menuPlato);
        menuPlato.setMenu(null);
    }
    ////////////
	
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Menu menu = (Menu) o;
	        return id == menu.id;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }
}
