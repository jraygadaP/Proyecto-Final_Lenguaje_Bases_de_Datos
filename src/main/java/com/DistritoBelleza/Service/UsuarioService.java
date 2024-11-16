package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.Usuario;
import com.DistritoBelleza.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.getUsuarios();
    }

    public void insertUsuario(String username, String clave, String nombre, String apellido, String correo, String telefono, String rutaImagen, Boolean activo) {
        usuarioRepository.insertUsuario(username, clave, nombre, apellido, correo, telefono, rutaImagen, activo);
    }

    public void updateUsuario(Long idUsuario, String username, String clave, String nombre, String apellido, String correo, String telefono, String rutaImagen, Boolean activo) {
        usuarioRepository.updateUsuario(idUsuario, username, clave, nombre, apellido, correo, telefono, rutaImagen, activo);
    }

    public void deleteUsuario(Long idUsuario) {
        usuarioRepository.deleteUsuario(idUsuario);
    }
}
