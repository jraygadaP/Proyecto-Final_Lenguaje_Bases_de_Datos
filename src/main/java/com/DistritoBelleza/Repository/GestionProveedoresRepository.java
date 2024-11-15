package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.GestionProveedores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class GestionProveedoresRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getGestionCall;
    private SimpleJdbcCall insertGestionCall;
    private SimpleJdbcCall updateGestionCall;
    private SimpleJdbcCall deleteGestionCall;

    @PostConstruct
    public void init() {
        getGestionCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_gestion_proveedores")
            .returningResultSet("p_cursor", (rs, rowNum) -> {
                GestionProveedores gestion = new GestionProveedores();
                gestion.setIdGestion(rs.getLong("id_gestion"));
                gestion.setIdProveedor(rs.getLong("id_proveedor"));
                gestion.setIdMaterial(rs.getLong("id_material"));
                return gestion;
            });

        insertGestionCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("insert_gestion_proveedores");

        updateGestionCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("update_gestion_proveedores");

        deleteGestionCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("delete_gestion_proveedores");
    }

    public List<GestionProveedores> getGestiones() {
        Map<String, Object> result = getGestionCall.execute();
        return (List<GestionProveedores>) result.get("p_cursor");
    }

    public void insertGestion(Long idProveedor, Long idMaterial) {
        insertGestionCall.execute(Map.of(
            "p_id_proveedor", idProveedor,
            "p_id_material", idMaterial
        ));
    }

    public void updateGestion(Long idGestion, Long idProveedor, Long idMaterial) {
        updateGestionCall.execute(Map.of(
            "p_id_gestion", idGestion,
            "p_id_proveedor", idProveedor,
            "p_id_material", idMaterial
        ));
    }

    public void deleteGestion(Long idGestion) {
        deleteGestionCall.execute(Map.of("p_id_gestion", idGestion));
    }
}
