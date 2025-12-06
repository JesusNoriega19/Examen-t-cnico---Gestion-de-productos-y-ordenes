package com.tienda.tienda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.tienda.dto.ProductoDTO;
import com.tienda.tienda.repository.ProductoRepositoryImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepositoryImpl repository;

    public List<ProductoDTO> listar() {
        return repository.listar();
    }

    public ProductoDTO obtener(Integer id) {
        return repository.obtener(id);
    }

    public void crear(ProductoDTO p) {
        repository.crear(p);
    }

    public void actualizar(Integer id, ProductoDTO p) {
        p.setIdProducto(id);
        repository.actualizar(p);
    }

    public void eliminar(Integer id) {
        repository.eliminar(id);
    }
}
