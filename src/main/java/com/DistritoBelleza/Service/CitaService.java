package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.Cita;
import com.DistritoBelleza.Repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getCitas() {
        return citaRepository.getCitas();
    }

    public void insertCita(Long idUsuario, String rutaImagen, String fecha, String hora, String descripcion, Boolean activo) {
        citaRepository.insertCita(idUsuario, rutaImagen, fecha, hora, descripcion, activo);
    }

    public void updateCita(Long idCita, Long idUsuario, String rutaImagen, String fecha, String hora, String descripcion, Boolean activo) {
        citaRepository.updateCita(idCita, idUsuario, rutaImagen, fecha, hora, descripcion, activo);
    }

    public void deleteCita(Long idCita) {
        citaRepository.deleteCita(idCita);
    }
}

