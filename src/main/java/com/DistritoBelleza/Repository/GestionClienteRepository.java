package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.GestionCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class GestionClienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getClientesCall;
    private SimpleJdbcCall insertClienteCall;
    private SimpleJdbcCall updateClienteCall;
    private SimpleJdbcCall deleteClienteCall;

    @PostConstruct
    public void init() {
        getClientesCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_gestion_clientes")
            .returningResultSet("p_cursor", (rs, rowNum) -> {
                GestionCliente cliente = new GestionCliente();
                cliente.setIdCliente(rs.getLong("id_cliente"));
                cliente.setIdUsuario(rs.getLong("id_usuario"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setNumeroFactura(rs.getString("numero_factura"));
                cliente.setCorreo(rs.getString("correo"));
                return cliente;
            });

        insertClienteCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("insert_gestion_cliente");

        updateClienteCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("update_gestion_cliente");

        deleteClienteCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("delete_gestion_cliente");
    }

    public List<GestionCliente> getClientes() {
        Map<String, Object> result = getClientesCall.execute();
        return (List<GestionCliente>) result.get("p_cursor");
    }

    public void insertCliente(Long idUsuario, String nombre, String numeroFactura, String correo) {
        insertClienteCall.execute(Map.of(
            "p_id_usuario", idUsuario,
            "p_nombre", nombre,
            "p_numero_factura", numeroFactura,
            "p_correo", correo
        ));
    }

    public void updateCliente(Long idCliente, Long idUsuario, String nombre, String numeroFactura, String correo) {
        updateClienteCall.execute(Map.of(
            "p_id_cliente", idCliente,
            "p_id_usuario", idUsuario,
            "p_nombre", nombre,
            "p_numero_factura", numeroFactura,
            "p_correo", correo
        ));
    }

    public void deleteCliente(Long idCliente) {
        deleteClienteCall.execute(Map.of("p_id_cliente", idCliente));
    }
}

