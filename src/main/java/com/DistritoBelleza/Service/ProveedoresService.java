package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.Proveedores;
import com.DistritoBelleza.Repository.ProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedoresService {

    @Autowired
    private ProveedoresRepository proveedoresRepository;

    public List<Proveedores> getProveedores() {
        return proveedoresRepository.getProveedores();
    }

    public void insertProveedor(String nombre) {
        proveedoresRepository.insertProveedor(nombre);
    }

    public void updateProveedor(Long idProveedor, String nombre) {
        proveedoresRepository.updateProveedor(idProveedor, nombre);
    }

    public void deleteProveedor(Long idProveedor) {
        proveedoresRepository.deleteProveedor(idProveedor);
    }
}

