package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.Factura;
import com.DistritoBelleza.Repository.FacturaRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> getFacturas() {
        return facturaRepository.getFacturas();
    }

public void insertFactura(Long numeroFactura, Long idEmpleado, Long idUsuario, Long idProducto, LocalDateTime fechaEmision, String detalleServicio) {
    // Validaciones de datos en el servicio
    if (idEmpleado == null || idUsuario == null || idProducto == null || fechaEmision == null || detalleServicio == null) {
        throw new IllegalArgumentException("Todos los campos son obligatorios y no pueden ser null.");
    }

    facturaRepository.insertFactura(numeroFactura, idEmpleado, idUsuario, idProducto, fechaEmision, detalleServicio);
}



    public void updateFactura(Long numeroFactura, Long idEmpleado, Long idUsuario, Long idProducto, String fechaEmision, String detalleServicio) {
        facturaRepository.updateFactura(numeroFactura, idEmpleado, idUsuario, idProducto, fechaEmision, detalleServicio);
    }

    public void deleteFactura(Long numeroFactura) {
        facturaRepository.deleteFactura(numeroFactura);
    }
}

