package com.DistritoBelleza.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository // ✅ Esta anotación es indispensable
public class AccesoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> buscarProductos(String patron) {
        String sql = "SELECT paquete_funciones.buscar_productos(?) FROM dual";
        String resultado = jdbcTemplate.queryForObject(sql, new Object[]{patron}, String.class);
        
        if (resultado != null && !resultado.startsWith("No se encontraron productos")) {
            return Arrays.asList(resultado.split(","));
        } else {
            return List.of(); // Retorna una lista vacía si no hay resultados
        }
    }
}



