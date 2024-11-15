package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.GestionProveedores;
import com.DistritoBelleza.Service.GestionProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gestiones-proveedores")
public class GestionProveedoresController {

    @Autowired
    private GestionProveedoresService gestionProveedoresService;

    @GetMapping
    public String listarGestiones(Model model) {
        model.addAttribute("gestiones", gestionProveedoresService.getGestiones());
        return "gestion/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaGestion(Model model) {
        model.addAttribute("gestion", new GestionProveedores());
        return "gestion/formulario";
    }

    @PostMapping
    public String insertarGestion(@ModelAttribute GestionProveedores gestion) {
        gestionProveedoresService.insertGestion(gestion.getIdProveedor(), gestion.getIdMaterial());
        return "redirect:/gestiones-proveedores";
    }

    @GetMapping("/editar/{idGestion}")
    public String mostrarFormularioEditarGestion(@PathVariable Long idGestion, Model model) {
        GestionProveedores gestion = gestionProveedoresService.getGestiones().stream()
                .filter(g -> g.getIdGestion().equals(idGestion))
                .findFirst()
                .orElse(null);

        if (gestion == null) {
            return "error/404";
        }

        model.addAttribute("gestion", gestion);
        return "gestion/formulario";
    }

    @PostMapping("/actualizar/{idGestion}")
    public String actualizarGestion(@PathVariable Long idGestion, @ModelAttribute GestionProveedores gestion) {
        gestionProveedoresService.updateGestion(idGestion, gestion.getIdProveedor(), gestion.getIdMaterial());
        return "redirect:/gestiones-proveedores";
    }

    @GetMapping("/eliminar/{idGestion}")
    public String eliminarGestion(@PathVariable Long idGestion) {
        gestionProveedoresService.deleteGestion(idGestion);
        return "redirect:/gestiones-proveedores";
    }
}
