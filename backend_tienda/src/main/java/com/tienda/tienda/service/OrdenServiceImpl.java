package com.tienda.tienda.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.tienda.tienda.dto.ConsultaOrdenDTO;
import com.tienda.tienda.dto.CrearOrdenDTO;
import com.tienda.tienda.dto.OrdenDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer crearOrden(CrearOrdenDTO request) {

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_ORDENES")
                .withProcedureName("CREAR_ORDEN")
                .declareParameters(
                        new SqlParameter("P_CLIENTE", Types.VARCHAR),
                        new SqlParameter("P_PRODUCTOS", Types.VARCHAR),
                        new SqlOutParameter("P_ID_ORDEN", Types.INTEGER)
                );
        StringBuilder productos = new StringBuilder();
        request.getProductos().forEach(item
                -> productos.append(item.getIdProducto())
                        .append(":")
                        .append(item.getCantidad())
                        .append("|")
        );
        Map<String, Object> params = new HashMap<>();
        params.put("P_CLIENTE", request.getCliente());
        params.put("P_PRODUCTOS", productos.toString());
        Map<String, Object> result = jdbcCall.execute(params);
        return (Integer) result.get("P_ID_ORDEN");
    }


    @Override
    public List<OrdenDTO> consultarOrdenes(ConsultaOrdenDTO request) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_ORDENES")
                .withProcedureName("CONSULTAR_ORDENES")
                .declareParameters(
                        new SqlParameter("P_CLIENTE", Types.VARCHAR),
                        new SqlParameter("P_FECHA_INICIO", Types.DATE),
                        new SqlParameter("P_FECHA_FIN", Types.DATE),
                        new SqlOutParameter("P_CURSOR", Types.REF_CURSOR)
                )
                .returningResultSet("P_CURSOR", (rs, rowNum) -> mapOrdenDTO(rs));
        Map<String, Object> params = new HashMap<>();
        params.put("P_CLIENTE", request.getCliente());
        params.put("P_FECHA_INICIO", new java.sql.Date(request.getFechaInicio().getTime()));
        params.put("P_FECHA_FIN", new java.sql.Date(request.getFechaFin().getTime()));
        Map<String, Object> result = jdbcCall.execute(params);
        return (List<OrdenDTO>) result.get("P_CURSOR");
    }


    private OrdenDTO mapOrdenDTO(ResultSet rs) throws SQLException {
        OrdenDTO dto = new OrdenDTO();
        dto.setIdOrden(rs.getInt("ID_ORDEN"));
        dto.setCliente(rs.getString("CLIENTE"));
        dto.setNombreProducto(rs.getString("NOMBRE_PRODUCTO"));
        dto.setFechaOrden(rs.getDate("FECHA_ORDEN").toLocalDate());
        dto.setIdProducto(rs.getLong("ID_PRODUCTO"));
        dto.setCantidad(rs.getInt("CANTIDAD"));

        return dto;
    }
}
