package com.dawes.modelo;

import java.time.LocalDate;
import java.util.List;

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
@Table(name="pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private LocalDate fechaCreacion;
	private double total;
	
	@OneToMany(mappedBy = "pedido")
	private List<MenuPedido> menus;
	
	@OneToMany(mappedBy = "pedido")
	private List<PlatoPedido> platos;
	
}
