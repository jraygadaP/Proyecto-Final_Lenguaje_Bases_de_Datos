package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.Devolucion;
import com.DistritoBelleza.Service.DevolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @GetMapping
    public String listarDevoluciones(Model model) {
        model.addAttribute("devoluciones", devolucionService.getDevoluciones());
        return "devoluciones/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaDevolucion(Model model) {
        model.addAttribute("devolucion", new Devolucion());
        return "devoluciones/formulario";
    }

    @PostMapping
    public String insertDevolucion(@ModelAttribute Devolucion devolucion) {
        devolucionService.insertDevolucion(
            devolucion.getIdCliente(),
            devolucion.getFechaDevolucion(),
            devolucion.getMotivo(),
            devolucion.getEstadoDevolucion()
        );
        return "redirect:/devoluciones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarDevolucion(@PathVariable Long id, Model model) {
        model.addAttribute("devolucion", devolucionService.getDevoluciones()
            .stream()
            .filter(d -> d.getIdDevolucion().equals(id))
            .findFirst()
            .orElse(null));
        return "devoluciones/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String updateDevolucion(@PathVariable Long id, @ModelAttribute Devolucion devolucion) {
        devolucionService.updateDevolucion(
            id,
            devolucion.getIdCliente(),
            devolucion.getFechaDevolucion(),
            devolucion.getMotivo(),
            devolucion.getEstadoDevolucion()
        );
        return "redirect:/devoluciones";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteDevolucion(@PathVariable Long id) {
        devolucionService.deleteDevolucion(id);
        return "redirect:/devoluciones";
    }
}
