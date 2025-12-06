package com.tienda.tienda.model;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDEN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEN")
    private Long idOrden;

    @Column(name = "CLIENTE", nullable = false)
    private String cliente;

    @Column(name = "FECHA_ORDEN", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOrden;

    @OneToMany(mappedBy = "orden")
    private List<OrdenDetalle> detalles;
}
