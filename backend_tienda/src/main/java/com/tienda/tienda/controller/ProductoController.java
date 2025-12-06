package com.tienda.tienda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.tienda.dto.ProductoDTO;
import com.tienda.tienda.service.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public List<ProductoDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProductoDTO obtener(@PathVariable Integer id) {
        return service.obtener(id);
    }

    @PostMapping
    public void crear(@RequestBody ProductoDTO p) {
        service.crear(p);
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable Integer id, @RequestBody ProductoDTO p) {
        service.actualizar(id, p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
