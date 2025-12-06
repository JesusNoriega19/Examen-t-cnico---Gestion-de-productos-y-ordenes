package com.tienda.tienda.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.tienda.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
