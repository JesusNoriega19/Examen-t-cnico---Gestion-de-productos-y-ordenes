package com.tienda.tienda.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.tienda.dto.ConsultaOrdenDTO;
import com.tienda.tienda.dto.CrearOrdenDTO;
import com.tienda.tienda.dto.OrdenDTO;
import com.tienda.tienda.service.OrdenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ordenes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrdenController {

    private final OrdenService ordenService;


    @PostMapping
    public ResponseEntity<Integer> crearOrden(@RequestBody CrearOrdenDTO request) {
        Integer idOrden = ordenService.crearOrden(request);
        return ResponseEntity.ok(idOrden);
    }

    @PostMapping("/consultar")
    public ResponseEntity<List<OrdenDTO>> consultarOrdenes(@RequestBody ConsultaOrdenDTO request) {
        List<OrdenDTO> lista = ordenService.consultarOrdenes(request);
        return ResponseEntity.ok(lista);
    }
}
