package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.Empleado;
import com.DistritoBelleza.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String listarEmpleados(Model model) {
        model.addAttribute("empleados", empleadoService.getEmpleados());
        return "empleados/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoEmpleado(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "empleados/formulario";
    }

    @PostMapping
    public String insertEmpleado(@ModelAttribute Empleado empleado) {
        empleadoService.insertEmpleado(
            empleado.getNombre(),
            empleado.getPrimerApellido(),
            empleado.getSegundoApellido(),
            empleado.getPuesto()
        );
        return "redirect:/empleados";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarEmpleado(@PathVariable Long id, Model model) {
        model.addAttribute("empleado", empleadoService.getEmpleados()
            .stream()
            .filter(e -> e.getIdEmpleado().equals(id))
            .findFirst()
            .orElse(null));
        return "empleados/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String updateEmpleado(@PathVariable Long id, @ModelAttribute Empleado empleado) {
        empleadoService.updateEmpleado(
            id,
            empleado.getNombre(),
            empleado.getPrimerApellido(),
            empleado.getSegundoApellido(),
            empleado.getPuesto()
        );
        return "redirect:/empleados";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteEmpleado(@PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
        return "redirect:/empleados";
    }
}