package com.dawes.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dawes.modelo.Categoria;
import com.dawes.modelo.Plato;


public interface PlatoRepository extends JpaRepository<Plato, Integer> {
	Optional<Plato> findById(int id);
	Optional<Plato> findByNombre(String nombre);
//	Optional<Plato> findByCategoria(Categoria categoria);	
	List<Plato> findByNombreContainsIgnoreCase(String cadena);
	
	@Query("select p from Plato p where p.categoria.id = ?1")
	public List<Plato> findByCategoriaId(int categoriaId);
	
	public List<Plato> findByCategoria(Categoria categoria);
	
	
	/////
	@Query("select p.id from Plato p")
	public List<Integer> obtenerIds();
	
}
