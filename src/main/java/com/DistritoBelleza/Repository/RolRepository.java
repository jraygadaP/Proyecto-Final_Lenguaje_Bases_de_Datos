package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class RolRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getRolesCall;
    private SimpleJdbcCall insertRolCall;
    private SimpleJdbcCall updateRolCall;
    private SimpleJdbcCall deleteRolCall;

    @PostConstruct
    public void init() {
        getRolesCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_roles")
            .returningResultSet("p_cursor", (rs, rowNum) -> {
                Rol rol = new Rol();
                rol.setIdRol(rs.getLong("id_rol"));
                rol.setIdUsuario(rs.getLong("id_usuario"));
                rol.setNombre(rs.getString("nombre"));
                return rol;
            });

        insertRolCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("insert_rol");

        updateRolCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("update_rol");

        deleteRolCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("delete_rol");
    }

    public List<Rol> getRoles() {
        Map<String, Object> result = getRolesCall.execute();
        return (List<Rol>) result.get("p_cursor");
    }

    public void insertRol(Long idUsuario, String nombre) {
        insertRolCall.execute(Map.of(
            "p_id_usuario", idUsuario,
            "p_nombre", nombre
        ));
    }

    public void updateRol(Long idRol, Long idUsuario, String nombre) {
        updateRolCall.execute(Map.of(
            "p_id_rol", idRol,
            "p_id_usuario", idUsuario,
            "p_nombre", nombre
        ));
    }

    public void deleteRol(Long idRol) {
        deleteRolCall.execute(Map.of("p_id_rol", idRol));
    }
}
