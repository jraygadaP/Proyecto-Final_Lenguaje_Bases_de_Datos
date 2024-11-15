package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Proveedores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class ProveedoresRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getProveedoresCall;
    private SimpleJdbcCall insertProveedorCall;
    private SimpleJdbcCall updateProveedorCall;
    private SimpleJdbcCall deleteProveedorCall;

    @PostConstruct
    public void init() {
        getProveedoresCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_proveedores")
            .returningResultSet("p_cursor", (rs, rowNum) -> {
                Proveedores p = new Proveedores();
                p.setIdProveedor(rs.getLong("id_proveedor"));
                p.setNombre(rs.getString("nombre"));
                return p;
            });

        insertProveedorCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("insert_proveedor");

        updateProveedorCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("update_proveedor");

        deleteProveedorCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("delete_proveedor");
    }

    public List<Proveedores> getProveedores() {
        Map<String, Object> result = getProveedoresCall.execute();
        return (List<Proveedores>) result.get("p_cursor");
    }

    public void insertProveedor(String nombre) {
        insertProveedorCall.execute(Map.of("p_nombre", nombre));
    }

    public void updateProveedor(Long idProveedor, String nombre) {
        updateProveedorCall.execute(Map.of(
            "p_id_proveedor", idProveedor,
            "p_nombre", nombre
        ));
    }

    public void deleteProveedor(Long idProveedor) {
        deleteProveedorCall.execute(Map.of("p_id_proveedor", idProveedor));
    }
}
