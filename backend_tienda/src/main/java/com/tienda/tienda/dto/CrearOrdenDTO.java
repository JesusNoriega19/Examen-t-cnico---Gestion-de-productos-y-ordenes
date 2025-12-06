package com.tienda.tienda.dto;
import java.util.List;

import lombok.Data;

@Data
public class CrearOrdenDTO {

    private String cliente;
    private List<ItemProducto> productos;

    @Data
    public static class ItemProducto {
        private Long idProducto;
        private Integer cantidad;
    }
}

