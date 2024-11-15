package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.SeguimientoPromociones;
import com.DistritoBelleza.Repository.SeguimientoPromocionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeguimientoPromocionesService {

    @Autowired
    private SeguimientoPromocionesRepository seguimientoPromocionesRepository;

    public List<SeguimientoPromociones> getSeguimientoPromociones() {
        return seguimientoPromocionesRepository.getSeguimientoPromociones();
    }

    public void insertSeguimientoPromociones(Long idProducto, String promocion, Integer duracion) {
        seguimientoPromocionesRepository.insertSeguimientoPromociones(idProducto, promocion, duracion);
    }

    public void updateSeguimientoPromociones(Long idPromociones, Long idProducto, String promocion, Integer duracion) {
        seguimientoPromocionesRepository.updateSeguimientoPromociones(idPromociones, idProducto, promocion, duracion);
    }

    public void deleteSeguimientoPromociones(Long idPromociones) {
        seguimientoPromocionesRepository.deleteSeguimientoPromociones(idPromociones);
    }
}
