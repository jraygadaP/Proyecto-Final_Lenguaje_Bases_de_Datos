package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.GestionCliente;
import com.DistritoBelleza.Repository.GestionClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionClienteService {

    @Autowired
    private GestionClienteRepository clienteRepository;

    public List<GestionCliente> getClientes() {
        return clienteRepository.getClientes();
    }

    public void insertCliente(Long idUsuario, String nombre, String numeroFactura, String correo) {
        clienteRepository.insertCliente(idUsuario, nombre, numeroFactura, correo);
    }

    public void updateCliente(Long idCliente, Long idUsuario, String nombre, String numeroFactura, String correo) {
        clienteRepository.updateCliente(idCliente, idUsuario, nombre, numeroFactura, correo);
    }

    public void deleteCliente(Long idCliente) {
        clienteRepository.deleteCliente(idCliente);
    }
}

