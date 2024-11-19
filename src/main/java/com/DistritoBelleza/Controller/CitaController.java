package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.Cita;
import com.DistritoBelleza.Service.CitaService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.getCitas());
        return "citas/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaCita(Model model) {
        model.addAttribute("cita", new Cita());
        return "citas/formulario";
    }

   @PostMapping
public String insertCita(
        @RequestParam("fecha") String fecha,
        @RequestParam("hora") String hora,
        @ModelAttribute Cita cita) {
    try {
        // Validar y combinar fecha y hora
        String fechaHora = fecha + "T" + hora; // Formato: yyyy-MM-ddTHH:mm
        LocalDateTime dateTime = LocalDateTime.parse(fechaHora);

        // Establecer los valores en la entidad Cita
        cita.setFecha(dateTime.toLocalDate().toString());
        cita.setHora(dateTime.toLocalTime().toString());

        // Guardar la cita usando el servicio
        citaService.insertCita(
                cita.getIdUsuario(),
                cita.getRutaImagen(),
                cita.getFecha(),
                cita.getHora(),
                cita.getDescripcion(),
                cita.getActivo()
        );

        return "redirect:/citas";
    } catch (Exception e) {
        // Log del error y redirección en caso de fallo
        e.printStackTrace();
        return "redirect:/citas/nueva?error=true";
    }
}

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCita(@PathVariable Long id, Model model) {
        model.addAttribute("cita", citaService.getCitas()
                .stream()
                .filter(c -> c.getIdCita().equals(id))
                .findFirst()
                .orElse(null));
        return "citas/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String updateCita(@PathVariable Long id, @ModelAttribute Cita cita) {
        citaService.updateCita(
                id,
                cita.getIdUsuario(),
                cita.getRutaImagen(),
                cita.getFecha(),
                cita.getHora(),
                cita.getDescripcion(),
                cita.getActivo()
        );
        return "redirect:/citas";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return "redirect:/citas";
    }
    
        @GetMapping("/{id}")
public String mostrarDetalleCita(@PathVariable Long id, Model model) {
    Cita cita = citaService.getCitas().stream()
            .filter(c -> c.getIdCita().equals(id))
            .findFirst()
            .orElse(null);
    if (cita == null) {
        return "error/404"; // Redirige a una página 404 personalizada si no se encuentra
    }
    model.addAttribute("Cita", cita);
    return "cita/detalle"; // Nombre de la plantilla de detalle
}
}

