package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.Devolucion;
import com.DistritoBelleza.Repository.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;

    public List<Devolucion> getDevoluciones() {
        return devolucionRepository.getDevoluciones();
    }

    public void insertDevolucion(Long idCliente, LocalDate fechaDevolucion, String motivo, String estadoDevolucion) {
        devolucionRepository.insertDevolucion(idCliente, fechaDevolucion, motivo, estadoDevolucion);
    }

    public void updateDevolucion(Long idDevolucion, Long idCliente, LocalDate fechaDevolucion, String motivo, String estadoDevolucion) {
        devolucionRepository.updateDevolucion(idDevolucion, idCliente, fechaDevolucion, motivo, estadoDevolucion);
    }

    public void deleteDevolucion(Long idDevolucion) {
        devolucionRepository.deleteDevolucion(idDevolucion);
    }
}
