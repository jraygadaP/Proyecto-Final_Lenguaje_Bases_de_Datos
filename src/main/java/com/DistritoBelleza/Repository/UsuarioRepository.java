package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getUsuariosCall;
    private SimpleJdbcCall insertUsuarioCall;
    private SimpleJdbcCall updateUsuarioCall;
    private SimpleJdbcCall deleteUsuarioCall;

    @PostConstruct
    public void init() {
        // Configuración del procedimiento GET_USUARIOS
        getUsuariosCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GET_USUARIOS")
                .returningResultSet("p_cursor", (rs, rowNum) -> {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getLong("Iden")); // Alias definido en el procedimiento
                    usuario.setUsername(rs.getString("usuario")); // Alias definido en el procedimiento
                    usuario.setNombre(rs.getString("nombre")); // Alias definido en el procedimiento
                    usuario.setCorreo(rs.getString("correo")); // Alias definido en el procedimiento
                    return usuario;
                });

        // Configuración de los demás procedimientos
        insertUsuarioCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insert_usuario");

        updateUsuarioCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("update_usuario");

        deleteUsuarioCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("delete_usuario");
    }

    public List<Usuario> getUsuarios() {
        Map<String, Object> result = getUsuariosCall.execute();
        return (List<Usuario>) result.get("p_cursor");
    }

    public void insertUsuario(String username, String clave, String nombre, String apellido, String correo, String telefono, String rutaImagen, Boolean activo) {
        insertUsuarioCall.execute(Map.of(
                "p_username", username,
                "p_clave", clave,
                "p_nombre", nombre,
                "p_apellido", apellido,
                "p_correo", correo,
                "p_telefono", telefono,
                "p_ruta_imagen", rutaImagen,
                "p_activo", activo
        ));
    }

    public void updateUsuario(Long idUsuario, String username, String clave, String nombre, String apellido, String correo, String telefono, String rutaImagen, Boolean activo) {
        updateUsuarioCall.execute(Map.of(
                "p_id_usuario", idUsuario,
                "p_username", username,
                "p_clave", clave,
                "p_nombre", nombre,
                "p_apellido", apellido,
                "p_correo", correo,
                "p_telefono", telefono,
                "p_ruta_imagen", rutaImagen,
                "p_activo", activo
        ));
    }

    public void deleteUsuario(Long idUsuario) {
        deleteUsuarioCall.execute(Map.of("p_id_usuario", idUsuario));
    }
}
