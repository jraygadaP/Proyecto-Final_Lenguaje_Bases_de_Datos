package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.InventarioMateriales;
import com.DistritoBelleza.Repository.InventarioMaterialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioMaterialesService {

    @Autowired
    private InventarioMaterialesRepository inventarioMaterialesRepository;

    public List<InventarioMateriales> getInventarioMateriales() {
        return inventarioMaterialesRepository.getInventarioMateriales();
    }

    public void insertInventarioMateriales(String tipoMaterial, Integer cantidad) {
        inventarioMaterialesRepository.insertInventarioMateriales(tipoMaterial, cantidad);
    }

    public void updateInventarioMateriales(Long idMaterial, String tipoMaterial, Integer cantidad) {
        inventarioMaterialesRepository.updateInventarioMateriales(idMaterial, tipoMaterial, cantidad);
    }

    public void deleteInventarioMateriales(Long idMaterial) {
        inventarioMaterialesRepository.deleteInventarioMateriales(idMaterial);
    }
}
