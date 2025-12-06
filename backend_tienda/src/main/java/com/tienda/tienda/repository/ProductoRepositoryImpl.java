package com.tienda.tienda.repository;

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
import org.springframework.stereotype.Repository;

import com.tienda.tienda.dto.ProductoDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductoRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;

    public List<ProductoDTO> listar() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_PRODUCTOS")
                .withProcedureName("LISTAR_PRODUCTOS")
                .declareParameters(
                        new SqlOutParameter("P_CURSOR", Types.REF_CURSOR)
                )
                .returningResultSet("P_CURSOR", (rs, rowNum) -> mapProducto(rs));

        Map<String, Object> result = jdbcCall.execute();
        return (List<ProductoDTO>) result.get("P_CURSOR");
    }

    public ProductoDTO obtener(Integer id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_PRODUCTOS")
                .withProcedureName("OBTENER_PRODUCTO")
                .declareParameters(
                        new SqlParameter("P_ID_PRODUCTO", Types.INTEGER),
                        new SqlOutParameter("P_CURSOR", Types.REF_CURSOR)
                )
                .returningResultSet("P_CURSOR", (rs, rowNum) -> mapProducto(rs));

        Map<String, Object> params = Map.of("P_ID_PRODUCTO", id);
        Map<String, Object> result = jdbcCall.execute(params);

        List<ProductoDTO> lista = (List<ProductoDTO>) result.get("P_CURSOR");
        return lista.isEmpty() ? null : lista.get(0);
    }

    public void crear(ProductoDTO p) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_PRODUCTOS")
                .withProcedureName("CREAR_PRODUCTO")
                .declareParameters(
                        new SqlParameter("P_NOMBRE", Types.VARCHAR),
                        new SqlParameter("P_PRECIO", Types.NUMERIC)
                );

        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", p.getNombre());
        params.put("P_PRECIO", p.getPrecio());

        jdbcCall.execute(params);
    }

    public void actualizar(ProductoDTO p) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_PRODUCTOS")
                .withProcedureName("ACTUALIZAR_PRODUCTO")
                .declareParameters(
                        new SqlParameter("P_ID_PRODUCTO", Types.INTEGER),
                        new SqlParameter("P_NOMBRE", Types.VARCHAR),
                        new SqlParameter("P_PRECIO", Types.NUMERIC)
                );

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PRODUCTO", p.getIdProducto());
        params.put("P_NOMBRE", p.getNombre());
        params.put("P_PRECIO", p.getPrecio());

        jdbcCall.execute(params);
    }

    public void eliminar(Integer id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_PRODUCTOS")
                .withProcedureName("ELIMINAR_PRODUCTO")
                .declareParameters(
                        new SqlParameter("P_ID_PRODUCTO", Types.INTEGER)
                );

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PRODUCTO", id);

        jdbcCall.execute(params);
    }


    private ProductoDTO mapProducto(ResultSet rs) throws SQLException {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(rs.getInt("ID_PRODUCTO"));
        dto.setNombre(rs.getString("NOMBRE"));
        dto.setPrecio(rs.getDouble("PRECIO"));
        return dto;
    }
}
