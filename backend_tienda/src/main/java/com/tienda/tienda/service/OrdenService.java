package com.tienda.tienda.service;

import com.tienda.tienda.dto.CrearOrdenDTO;
import com.tienda.tienda.dto.OrdenDTO;
import com.tienda.tienda.dto.ConsultaOrdenDTO;

import java.util.List;

public interface OrdenService {

    Integer crearOrden(CrearOrdenDTO request);

    List<OrdenDTO> consultarOrdenes(ConsultaOrdenDTO request);
}
