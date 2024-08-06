package com.dawes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="menus_pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private double cantidad;
	private double precio;
	private double total;
	
	@ManyToOne
	@JoinColumn(name="idpedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="idmenu")
	private Menu menu;
}
