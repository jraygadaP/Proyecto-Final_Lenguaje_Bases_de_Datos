package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.SeguimientoPromociones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class SeguimientoPromocionesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getSeguimientoPromocionesCall;
    private SimpleJdbcCall insertSeguimientoPromocionesCall;
    private SimpleJdbcCall updateSeguimientoPromocionesCall;
    private SimpleJdbcCall deleteSeguimientoPromocionesCall;

    @PostConstruct
    public void init() {
        getSeguimientoPromocionesCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_seguimiento_promociones")
            .returningResultSet("p_cursor", (rs, rowNum) -> {
                SeguimientoPromociones sp = new SeguimientoPromociones();
                sp.setIdPromociones(rs.getLong("id_promociones"));
                sp.setIdProducto(rs.getLong("id_producto"));
                sp.setPromocion(rs.getString("promocion"));
                sp.setDuracion(rs.getInt("duracion"));
                return sp;
            });

        insertSeguimientoPromocionesCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("insert_seguimiento_promociones");

        updateSeguimientoPromocionesCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("update_seguimiento_promociones");

        deleteSeguimientoPromocionesCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("delete_seguimiento_promociones");
    }

    public List<SeguimientoPromociones> getSeguimientoPromociones() {
        Map<String, Object> result = getSeguimientoPromocionesCall.execute();
        return (List<SeguimientoPromociones>) result.get("p_cursor");
    }

    public void insertSeguimientoPromociones(Long idProducto, String promocion, Integer duracion) {
        insertSeguimientoPromocionesCall.execute(Map.of(
            "p_id_producto", idProducto,
            "p_promocion", promocion,
            "p_duracion", duracion
        ));
    }

    public void updateSeguimientoPromociones(Long idPromociones, Long idProducto, String promocion, Integer duracion) {
        updateSeguimientoPromocionesCall.execute(Map.of(
            "p_id_promociones", idPromociones,
            "p_id_producto", idProducto,
            "p_promocion", promocion,
            "p_duracion", duracion
        ));
    }

    public void deleteSeguimientoPromociones(Long idPromociones) {
        deleteSeguimientoPromocionesCall.execute(Map.of("p_id_promociones", idPromociones));
    }
}
