package com.DistritoBelleza.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class AccesoRepository {

    private final SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public AccesoRepository(JdbcTemplate jdbcTemplate) {
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("buscar_productos") // Nombre de la función
                .withCatalogName("paquete_funciones"); // Nombre del paquete PL/SQL
    }

    /**
     * Llama a la función buscar_productos del paquete PL/SQL
     *
     * @param patron El patrón de búsqueda que se quiere buscar en los productos
     * @return Lista de productos que coinciden con el patrón
     */
    public List<String> buscarProductos(String patron) {
        // Ejecutar la función y recibir la respuesta
        String resultado = simpleJdbcCall.executeFunction(String.class, patron);
        
        if (resultado != null && !resultado.startsWith("No se encontraron productos")) {
            // Dividimos la cadena resultado en una lista usando la coma como separador
            return Arrays.asList(resultado.split(","));
        } else {
            return List.of(); // Retorna una lista vacía si no hay resultados
        }
    }
}


