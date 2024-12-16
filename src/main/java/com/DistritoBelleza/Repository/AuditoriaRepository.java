    package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Auditoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuditoriaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Auditoria> getAuditoria() {
        // Ajustar la consulta SQL para usar el nombre correcto de las columnas
        String sql = "SELECT id_auditoria AS id, tabla_afectada, accion, datos_afectados, fecha_operacion AS fecha FROM auditoria";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Auditoria auditoria = new Auditoria();
            auditoria.setId(rs.getLong("id"));
            auditoria.setTablaAfectada(rs.getString("tabla_afectada"));
            auditoria.setAccion(rs.getString("accion"));
            auditoria.setDatosAfectados(rs.getString("datos_afectados"));
            auditoria.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
            return auditoria;
        });
    }
}
