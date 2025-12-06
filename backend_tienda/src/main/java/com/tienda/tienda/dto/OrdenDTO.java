package com.tienda.tienda.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrdenDTO {

    private Integer idOrden;
    private String cliente;
        private String nombreProducto;
    private LocalDate fechaOrden;
    private Long idProducto;
    private Integer cantidad;
}
