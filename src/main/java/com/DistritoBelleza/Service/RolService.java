package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.Rol;
import com.DistritoBelleza.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> getRoles() {
        return rolRepository.getRoles();
    }

    public void insertRol(Long idUsuario, String nombre) {
        rolRepository.insertRol(idUsuario, nombre);
    }

    public void updateRol(Long idRol, Long idUsuario, String nombre) {
        rolRepository.updateRol(idRol, idUsuario, nombre);
    }

    public void deleteRol(Long idRol) {
        rolRepository.deleteRol(idRol);
    }
}

