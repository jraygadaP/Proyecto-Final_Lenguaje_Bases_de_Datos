package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Cita;
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
public class CitaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getCitasCall;
    private SimpleJdbcCall insertCitaCall;
    private SimpleJdbcCall updateCitaCall;
    private SimpleJdbcCall deleteCitaCall;

    @PostConstruct
    public void init() {
        getCitasCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_citas")
                .returningResultSet("p_cursor", (rs, rowNum) -> {
                    Cita cita = new Cita();
                    cita.setIdCita(rs.getLong("id_cita"));
                    cita.setIdUsuario(rs.getLong("id_usuario"));
                    cita.setRutaImagen(rs.getString("ruta_imagen"));
                    cita.setFecha(rs.getString("fecha"));
                    cita.setHora(rs.getString("hora"));
                    cita.setDescripcion(rs.getString("descripcion"));
                    cita.setActivo(rs.getBoolean("activo"));
                    return cita;
                });

        insertCitaCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insert_cita");

        updateCitaCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("update_cita");

        deleteCitaCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("delete_cita");
    }

    public List<Cita> getCitas() {
        Map<String, Object> result = getCitasCall.execute();
        return (List<Cita>) result.get("p_cursor");
    }

    public void insertCita(Long idUsuario, String rutaImagen, String fecha, String hora, String descripcion, Boolean activo) {
        LocalDateTime dateTime = LocalDateTime.parse(fecha + "T" + hora); // Combina fecha y hora
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        insertCitaCall.execute(Map.of(
                "p_id_usuario", idUsuario,
                "p_ruta_imagen", rutaImagen,
                "p_fecha", timestamp,
                "p_hora", timestamp,
                "p_descripcion", descripcion,
                "p_activo", activo
        ));
    }

    public void updateCita(Long idCita, Long idUsuario, String rutaImagen, String fecha, String hora, String descripcion, Boolean activo) {
        updateCitaCall.execute(Map.of(
                "p_id_cita", idCita,
                "p_id_usuario", idUsuario,
                "p_ruta_imagen", rutaImagen,
                "p_fecha", fecha,
                "p_hora", hora,
                "p_descripcion", descripcion,
                "p_activo", activo
        ));
    }

    public void deleteCita(Long idCita) {
        deleteCitaCall.execute(Map.of("p_id_cita", idCita));
    }
}
