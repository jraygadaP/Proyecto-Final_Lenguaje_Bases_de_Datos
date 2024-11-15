package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.GestionProveedores;
import com.DistritoBelleza.Repository.GestionProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionProveedoresService {

    @Autowired
    private GestionProveedoresRepository gestionProveedoresRepository;

    public List<GestionProveedores> getGestiones() {
        return gestionProveedoresRepository.getGestiones();
    }

    public void insertGestion(Long idProveedor, Long idMaterial) {
        gestionProveedoresRepository.insertGestion(idProveedor, idMaterial);
    }

    public void updateGestion(Long idGestion, Long idProveedor, Long idMaterial) {
        gestionProveedoresRepository.updateGestion(idGestion, idProveedor, idMaterial);
    }

    public void deleteGestion(Long idGestion) {
        gestionProveedoresRepository.deleteGestion(idGestion);
    }
}
