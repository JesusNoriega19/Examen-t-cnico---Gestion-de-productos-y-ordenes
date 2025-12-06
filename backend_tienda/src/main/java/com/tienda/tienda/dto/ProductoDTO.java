package com.tienda.tienda.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Integer idProducto;
    private String nombre;
    private Double precio;
}