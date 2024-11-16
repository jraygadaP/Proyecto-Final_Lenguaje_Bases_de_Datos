package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.Rol;
import com.DistritoBelleza.Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public String listarRoles(Model model) {
        model.addAttribute("roles", rolService.getRoles());
        return "roles/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoRol(Model model) {
        model.addAttribute("rol", new Rol());
        return "roles/formulario";
    }

    @PostMapping
    public String insertRol(@ModelAttribute Rol rol) {
        rolService.insertRol(rol.getIdUsuario(), rol.getNombre());
        return "redirect:/roles";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarRol(@PathVariable Long id, Model model) {
        model.addAttribute("rol", rolService.getRoles()
            .stream()
            .filter(r -> r.getIdRol().equals(id))
            .findFirst()
            .orElse(null));
        return "roles/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String updateRol(@PathVariable Long id, @ModelAttribute Rol rol) {
        rolService.updateRol(id, rol.getIdUsuario(), rol.getNombre());
        return "redirect:/roles";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteRol(@PathVariable Long id) {
        rolService.deleteRol(id);
        return "redirect:/roles";
    }
}

