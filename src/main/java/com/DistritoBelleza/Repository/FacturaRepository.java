package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Factura;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class FacturaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getFacturasCall;
    private SimpleJdbcCall insertFacturaCall;
    private SimpleJdbcCall updateFacturaCall;
    private SimpleJdbcCall deleteFacturaCall;

    @PostConstruct
    public void init() {
        getFacturasCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_facturas")
                .returningResultSet("p_cursor", (rs, rowNum) -> {
                    Factura factura = new Factura();
                    factura.setNumeroFactura(rs.getLong("numero_factura"));
                    factura.setIdEmpleado(rs.getLong("id_empleado"));
                    factura.setIdUsuario(rs.getLong("id_usuario"));
                    factura.setIdProducto(rs.getLong("id_producto"));
                    factura.setFechaEmision(rs.getString("fecha_emision"));
                    factura.setDetalleServicio(rs.getString("detalle_servicio"));
                    return factura;
                });

        insertFacturaCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insert_factura");

        updateFacturaCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("update_factura");

        deleteFacturaCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("delete_factura");
    }

    public List<Factura> getFacturas() {
        Map<String, Object> result = getFacturasCall.execute();
        return (List<Factura>) result.get("p_cursor");
    }

    public void insertFactura(Long numeroFactura, Long idEmpleado, Long idUsuario, Long idProducto, LocalDateTime fechaEmision, String detalleServicio) {
        insertFacturaCall.execute(Map.of(
                "p_numero_factura", numeroFactura,
                "p_id_empleado", idEmpleado,
                "p_id_usuario", idUsuario,
                "p_id_producto", idProducto,
                "p_fecha_emision", Timestamp.valueOf(fechaEmision),
                "p_detalle_servicio", detalleServicio
        ));
    }

    public void updateFactura(Long numeroFactura, Long idEmpleado, Long idUsuario, Long idProducto, String fechaEmision, String detalleServicio) {
        updateFacturaCall.execute(Map.of(
                "p_numero_factura", numeroFactura,
                "p_id_empleado", idEmpleado,
                "p_id_usuario", idUsuario,
                "p_id_producto", idProducto,
                "p_fecha_emision", fechaEmision,
                "p_detalle_servicio", detalleServicio
        ));
    }

    public void deleteFactura(Long numeroFactura) {
        deleteFacturaCall.execute(Map.of("p_numero_factura", numeroFactura));
    }
}
