package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class CategoriaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getCategoriasCall;
    private SimpleJdbcCall insertCategoriaCall;
    private SimpleJdbcCall updateCategoriaCall;
    private SimpleJdbcCall deleteCategoriaCall;

    @PostConstruct
    public void init() {
        getCategoriasCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_Categorias")
            .returningResultSet("p_cursor", (rs, rowNum) -> {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getLong("id_categoria"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setRutaImagen(rs.getString("ruta_imagen"));
                categoria.setActivo(rs.getBoolean("activo"));
                return categoria;
            });

        insertCategoriaCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("insert_categoria");

        updateCategoriaCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("update_categoria");

        deleteCategoriaCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("delete_categoria");
    }

    public List<Categoria> getCategorias() {
        Map<String, Object> result = getCategoriasCall.execute();
        return (List<Categoria>) result.get("p_cursor");
    }

    public void insertCategoria(String descripcion, String rutaImagen, Boolean activo) {
        insertCategoriaCall.execute(Map.of(
            "p_descripcion", descripcion,
            "p_ruta_imagen", rutaImagen,
            "p_activo", activo
        ));
    }

    public void updateCategoria(Long idCategoria, String descripcion, String rutaImagen, Boolean activo) {
        updateCategoriaCall.execute(Map.of(
            "p_id_categoria", idCategoria,
            "p_descripcion", descripcion,
            "p_ruta_imagen", rutaImagen,
            "p_activo", activo
        ));
    }

    public void deleteCategoria(Long idCategoria) {
        deleteCategoriaCall.execute(Map.of("p_id_categoria", idCategoria));
    }
}
