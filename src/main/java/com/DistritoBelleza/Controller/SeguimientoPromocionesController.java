package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.SeguimientoPromociones;
import com.DistritoBelleza.Service.SeguimientoPromocionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/promociones")
public class SeguimientoPromocionesController {

    @Autowired
    private SeguimientoPromocionesService seguimientoPromocionesService;

    @GetMapping
    public String listarPromociones(Model model) {
        model.addAttribute("promociones", seguimientoPromocionesService.getSeguimientoPromociones());
        return "promocion/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaPromocion(Model model) {
        model.addAttribute("promocion", new SeguimientoPromociones());
        return "promocion/formulario";
    }

    @PostMapping
    public String insertarPromocion(@ModelAttribute SeguimientoPromociones promocion) {
        seguimientoPromocionesService.insertSeguimientoPromociones(
            promocion.getIdProducto(),
            promocion.getPromocion(),
            promocion.getDuracion()
        );
        return "redirect:/promociones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPromocion(@PathVariable Long id, Model model) {
        SeguimientoPromociones promocion = seguimientoPromocionesService.getSeguimientoPromociones().stream()
                .filter(p -> p.getIdPromociones().equals(id))
                .findFirst()
                .orElse(null);

        if (promocion == null) {
            return "error/404";
        }

        model.addAttribute("promocion", promocion);
        return "promocion/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPromocion(@PathVariable Long id, @ModelAttribute SeguimientoPromociones promocion) {
        seguimientoPromocionesService.updateSeguimientoPromociones(
            id,
            promocion.getIdProducto(),
            promocion.getPromocion(),
            promocion.getDuracion()
        );
        return "redirect:/promociones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPromocion(@PathVariable Long id) {
        seguimientoPromocionesService.deleteSeguimientoPromociones(id);
        return "redirect:/promociones";
    }

    @GetMapping("/{id}")
    public String mostrarDetallePromocion(@PathVariable Long id, Model model) {
        SeguimientoPromociones promocion = seguimientoPromocionesService.getSeguimientoPromociones().stream()
                .filter(p -> p.getIdPromociones().equals(id))
                .findFirst()
                .orElse(null);

        if (promocion == null) {
            return "error/404";
        }

        model.addAttribute("promocion", promocion);
        return "promocion/detalle";
    }
}
