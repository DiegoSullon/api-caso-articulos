package com.caso.articulos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "articulo")
@AllArgsConstructor
@NoArgsConstructor
public class Articulo {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter @Setter
	private int idArticulo;
	@Getter @Setter
	private String codigo;
	@Getter @Setter
	private String nombre;
	@Getter @Setter
	private double precioVenta;
	@Getter @Setter
	private int stock;
	@Getter @Setter
	private String descripcion;
	@Getter @Setter
	private String imagen;
	@Getter @Setter
	private boolean estado;
	
}