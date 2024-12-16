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
    private SimpleJdbcCall getInventarioDisponibleCall;
    private SimpleJdbcCall getPromocionesInactivasCall;

    @PostConstruct
    public void init() {
        // Configuración para obtener todos los productos
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
        

        // Configuración para insertar un producto
        insertProductoCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insert_producto");

        // Configuración para actualizar un producto
        updateProductoCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("update_producto");

        // Configuración para eliminar un producto
        deleteProductoCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("delete_producto");

        // Configuración para obtener el inventario disponible desde la vista
getInventarioDisponibleCall = new SimpleJdbcCall(jdbcTemplate)
    .withProcedureName("get_inventario_disponible")
    .returningResultSet("p_cursor", (rs, rowNum) -> {
        Producto producto = new Producto();
        producto.setIdProducto(rs.getLong("id_producto"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setExistencia(rs.getInt("existencia"));
        return producto;
    });

getPromocionesInactivasCall = new SimpleJdbcCall(jdbcTemplate)
        .withProcedureName("get_promociones_inactivas")
        .returningResultSet("p_cursor", (rs, rowNum) -> {
            Producto producto = new Producto();
            producto.setIdProducto(rs.getLong("id_producto"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setDuracion(rs.getInt("duracion"));
            return producto;
        });

}

    // Obtener todos los productos
    public List<Producto> getProductos() {
        Map<String, Object> result = getProductosCall.execute();
        return (List<Producto>) result.get("p_cursor");
    }

    // Insertar un producto
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

    // Actualizar un producto
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

    // Eliminar un producto
    public void deleteProducto(Long idProducto) {
        deleteProductoCall.execute(Map.of("p_id_producto", idProducto));
    }

    // Obtener inventario disponible
    public List<Producto> getInventarioDisponible() {
        Map<String, Object> result = getInventarioDisponibleCall.execute();
        return (List<Producto>) result.get("p_cursor");
    }
    
    public List<Producto> getPromocionesInactivas() {
    Map<String, Object> result = getPromocionesInactivasCall.execute();
    return (List<Producto>) result.get("p_cursor");
}
}

