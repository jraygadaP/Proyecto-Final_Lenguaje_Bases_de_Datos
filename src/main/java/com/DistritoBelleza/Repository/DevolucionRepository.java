package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Devolucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class DevolucionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getDevolucionesCall;
    private SimpleJdbcCall insertDevolucionCall;
    private SimpleJdbcCall updateDevolucionCall;
    private SimpleJdbcCall deleteDevolucionCall;

    @PostConstruct
    public void init() {
        getDevolucionesCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_devoluciones")
            .returningResultSet("p_cursor", (rs, rowNum) -> {
                Devolucion devolucion = new Devolucion();
                devolucion.setIdDevolucion(rs.getLong("id_devolucion"));
                devolucion.setIdCliente(rs.getLong("id_cliente"));
                devolucion.setFechaDevolucion(rs.getDate("fecha_devolucion").toLocalDate());
                devolucion.setMotivo(rs.getString("motivo"));
                devolucion.setEstadoDevolucion(rs.getString("estado_devolucion"));
                return devolucion;
            });

        insertDevolucionCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("insert_devolucion");

        updateDevolucionCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("update_devolucion");

        deleteDevolucionCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("delete_devolucion");
    }

    public List<Devolucion> getDevoluciones() {
        Map<String, Object> result = getDevolucionesCall.execute();
        return (List<Devolucion>) result.get("p_cursor");
    }

    public void insertDevolucion(Long idCliente, LocalDate fechaDevolucion, String motivo, String estadoDevolucion) {
        insertDevolucionCall.execute(Map.of(
            "p_id_cliente", idCliente,
            "p_fecha_devolucion", fechaDevolucion,
            "p_motivo", motivo,
            "p_estado_devolucion", estadoDevolucion
        ));
    }

    public void updateDevolucion(Long idDevolucion, Long idCliente, LocalDate fechaDevolucion, String motivo, String estadoDevolucion) {
        updateDevolucionCall.execute(Map.of(
            "p_id_devolucion", idDevolucion,
            "p_id_cliente", idCliente,
            "p_fecha_devolucion", fechaDevolucion,
            "p_motivo", motivo,
            "p_estado_devolucion", estadoDevolucion
        ));
    }

    public void deleteDevolucion(Long idDevolucion) {
        deleteDevolucionCall.execute(Map.of("p_id_devolucion", idDevolucion));
    }
}
