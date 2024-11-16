package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class EmpleadoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getEmpleadosCall;
    private SimpleJdbcCall insertEmpleadoCall;
    private SimpleJdbcCall updateEmpleadoCall;
    private SimpleJdbcCall deleteEmpleadoCall;

    @PostConstruct
    public void init() {
        getEmpleadosCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_empleados")
            .returningResultSet("p_cursor", (rs, rowNum) -> {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getLong("id_empleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setPrimerApellido(rs.getString("primer_apellido"));
                empleado.setSegundoApellido(rs.getString("segundo_apellido"));
                empleado.setPuesto(rs.getString("puesto"));
                return empleado;
            });

        insertEmpleadoCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("insert_empleado");

        updateEmpleadoCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("update_empleado");

        deleteEmpleadoCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("delete_empleado");
    }

    public List<Empleado> getEmpleados() {
        Map<String, Object> result = getEmpleadosCall.execute();
        return (List<Empleado>) result.get("p_cursor");
    }

    public void insertEmpleado(String nombre, String primerApellido, String segundoApellido, String puesto) {
        insertEmpleadoCall.execute(Map.of(
            "p_nombre", nombre,
            "p_primer_apellido", primerApellido,
            "p_segundo_apellido", segundoApellido,
            "p_puesto", puesto
        ));
    }

    public void updateEmpleado(Long idEmpleado, String nombre, String primerApellido, String segundoApellido, String puesto) {
        updateEmpleadoCall.execute(Map.of(
            "p_id_empleado", idEmpleado,
            "p_nombre", nombre,
            "p_primer_apellido", primerApellido,
            "p_segundo_apellido", segundoApellido,
            "p_puesto", puesto
        ));
    }

    public void deleteEmpleado(Long idEmpleado) {
        deleteEmpleadoCall.execute(Map.of("p_id_empleado", idEmpleado));
    }
}
