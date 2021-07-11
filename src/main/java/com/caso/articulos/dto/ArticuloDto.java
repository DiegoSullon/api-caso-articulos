package com.caso.articulos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ArticuloDto {
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
	@Setter
	private boolean estado;

    public boolean getEstado() {
        return this.estado;
    }
}

