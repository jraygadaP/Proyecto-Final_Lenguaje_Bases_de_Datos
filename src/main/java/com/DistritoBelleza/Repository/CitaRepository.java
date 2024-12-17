package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Cita;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private SimpleJdbcCall getCitasAtendidasCall;

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

        getCitasAtendidasCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_citas_atendidas")
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

    public List<Cita> getCitasAtendidas() {
        Map<String, Object> result = getCitasAtendidasCall.execute();
        return (List<Cita>) result.get("p_cursor");
    }

    public void insertCita(Long idUsuario, String rutaImagen, String fecha, String hora, String descripcion, Boolean activo) {
    try {
        // Agregar segundos si no están presentes
        String horaCompleta = hora.length() == 5 ? hora + ":00" : hora; // Asegura el formato HH:mm:ss
        String fechaHora = fecha + " " + horaCompleta;

        // Convertir a LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(fechaHora, formatter);
        Timestamp timestamp = Timestamp.valueOf(dateTime);

        // Ejecutar el procedimiento almacenado
        insertCitaCall.execute(Map.of(
                "p_id_usuario", idUsuario,
                "p_ruta_imagen", rutaImagen,
                "p_fecha", timestamp,
                "p_hora", horaCompleta,
                "p_descripcion", descripcion,
                "p_activo", activo
        ));
    } catch (Exception e) {
        throw new RuntimeException("Error al insertar la cita: " + e.getMessage(), e);
    }
}



    public void updateCita(Long idCita, Long idUsuario, String rutaImagen, String fecha, String hora, String descripcion, Boolean activo) {
    try {
        // Combinar fecha y hora y convertir a Timestamp
        String fechaHora = fecha + "T" + hora; // Formato ISO-8601
        LocalDateTime dateTime = LocalDateTime.parse(fechaHora);
        Timestamp timestamp = Timestamp.valueOf(dateTime);

        // Ejecutar el procedimiento almacenado
        updateCitaCall.execute(Map.of(
                "p_id_cita", idCita,
                "p_id_usuario", idUsuario,
                "p_ruta_imagen", rutaImagen,
                "p_fecha", timestamp,
                "p_hora", hora, // Si la hora sola se requiere como String
                "p_descripcion", descripcion,
                "p_activo", activo
        ));
    } catch (Exception e) {
        throw new RuntimeException("Error al actualizar la cita: " + e.getMessage(), e);
    }
}


    public void deleteCita(Long idCita) {
        deleteCitaCall.execute(Map.of("p_id_cita", idCita));
    }
}
