package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class ProductoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getProductosCall;
    private SimpleJdbcCall insertProductoCall;
    private SimpleJdbcCall updateProductoCall;
    private SimpleJdbcCall deleteProductoCall;

    @PostConstruct
    public void init() {
        getProductosCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_productos")
            .returningResultSet("p_cursor", (rs, rowNum) -> {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getLong("id_producto"));
                producto.setIdCategoria(rs.getLong("id_categoria"));
                producto.setIdMaterial(rs.getLong("id_material"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setDetalle(rs.getString("detalle"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setExistencia(rs.getInt("existencia"));
                producto.setRutaImagen(rs.getString("ruta_imagen"));
                producto.setActivo(rs.getBoolean("activo"));
                return producto;
            });

        insertProductoCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("insert_producto");

        updateProductoCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("update_producto");

        deleteProductoCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("delete_producto");
    }

    public List<Producto> getProductos() {
        Map<String, Object> result = getProductosCall.execute();
        return (List<Producto>) result.get("p_cursor");
    }

    public void insertProducto(Long idCategoria, Long idMaterial, String descripcion, String detalle, Double precio, Integer existencia, String rutaImagen, Boolean activo) {
        insertProductoCall.execute(Map.of(
            "p_id_categoria", idCategoria,
            "p_id_material", idMaterial,
            "p_descripcion", descripcion,
            "p_detalle", detalle,
            "p_precio", precio,
            "p_existencia", existencia,
            "p_ruta_imagen", rutaImagen,
            "p_activo", activo ? 1 : 0
        ));
    }

    public void updateProducto(Long idProducto, Long idCategoria, Long idMaterial, String descripcion, String detalle, Double precio, Integer existencia, String rutaImagen, Boolean activo) {
        updateProductoCall.execute(Map.of(
            "p_id_producto", idProducto,
            "p_id_categoria", idCategoria,
            "p_id_material", idMaterial,
            "p_descripcion", descripcion,
            "p_detalle", detalle,
            "p_precio", precio,
            "p_existencia", existencia,
            "p_ruta_imagen", rutaImagen,
            "p_activo", activo ? 1 : 0
        ));
    }

    public void deleteProducto(Long idProducto) {
        deleteProductoCall.execute(Map.of("p_id_producto", idProducto));
    }
}
