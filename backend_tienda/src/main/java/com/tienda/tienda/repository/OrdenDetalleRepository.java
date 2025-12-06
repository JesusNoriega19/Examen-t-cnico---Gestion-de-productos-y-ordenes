package com.tienda.tienda.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.tienda.model.OrdenDetalle;

@Repository
public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalle, Long> {
}
