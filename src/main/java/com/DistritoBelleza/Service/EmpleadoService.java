package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.Empleado;
import com.DistritoBelleza.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> getEmpleados() {
        return empleadoRepository.getEmpleados();
    }

    public void insertEmpleado(String nombre, String primerApellido, String segundoApellido, String puesto) {
        empleadoRepository.insertEmpleado(nombre, primerApellido, segundoApellido, puesto);
    }

    public void updateEmpleado(Long idEmpleado, String nombre, String primerApellido, String segundoApellido, String puesto) {
        empleadoRepository.updateEmpleado(idEmpleado, nombre, primerApellido, segundoApellido, puesto);
    }

    public void deleteEmpleado(Long idEmpleado) {
        empleadoRepository.deleteEmpleado(idEmpleado);
    }
}
