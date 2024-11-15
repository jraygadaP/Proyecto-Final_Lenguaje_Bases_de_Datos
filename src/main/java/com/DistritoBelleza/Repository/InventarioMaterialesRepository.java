package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.InventarioMateriales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class InventarioMaterialesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getInventarioMaterialesCall;
    private SimpleJdbcCall insertInventarioMaterialesCall;
    private SimpleJdbcCall updateInventarioMaterialesCall;
    private SimpleJdbcCall deleteInventarioMaterialesCall;

    @PostConstruct
    public void init() {
        getInventarioMaterialesCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_inventario_materiales")
            .returningResultSet("p_cursor", (rs, rowNum) -> {
                InventarioMateriales im = new InventarioMateriales();
                im.setIdMaterial(rs.getLong("id_material"));
                im.setTipoMaterial(rs.getString("tipo_material"));
                im.setCantidad(rs.getInt("cantidad"));
                return im;
            });

        insertInventarioMaterialesCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("insert_inventario_materiales");

        updateInventarioMaterialesCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("update_inventario_materiales");

        deleteInventarioMaterialesCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("delete_inventario_materiales");
    }

    public List<InventarioMateriales> getInventarioMateriales() {
        Map<String, Object> result = getInventarioMaterialesCall.execute();
        return (List<InventarioMateriales>) result.get("p_cursor");
    }

    public void insertInventarioMateriales(String tipoMaterial, Integer cantidad) {
        insertInventarioMaterialesCall.execute(Map.of("p_tipo_material", tipoMaterial, "p_cantidad", cantidad));
    }

    public void updateInventarioMateriales(Long idMaterial, String tipoMaterial, Integer cantidad) {
        updateInventarioMaterialesCall.execute(Map.of(
            "p_id_material", idMaterial,
            "p_tipo_material", tipoMaterial,
            "p_cantidad", cantidad
        ));
    }

    public void deleteInventarioMateriales(Long idMaterial) {
        deleteInventarioMaterialesCall.execute(Map.of("p_id_material", idMaterial));
    }
}
