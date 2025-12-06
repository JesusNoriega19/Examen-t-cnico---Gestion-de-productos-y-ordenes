package com.tienda.tienda.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ConsultaOrdenDTO {

    private String cliente;
    private Date fechaInicio;
    private Date fechaFin;
}
