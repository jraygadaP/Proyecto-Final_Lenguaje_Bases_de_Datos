package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.Usuario;
import com.DistritoBelleza.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.getUsuarios());
        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    @PostMapping
    public String insertUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.insertUsuario(
            usuario.getUsername(),
            usuario.getClave(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getCorreo(),
            usuario.getTelefono(),
            usuario.getRutaImagen(),
            usuario.getActivo()
        );
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.getUsuarios()
            .stream()
            .filter(u -> u.getIdUsuario().equals(id))
            .findFirst()
            .orElse(null));
        return "usuarios/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String updateUsuario(@PathVariable Long id, @ModelAttribute Usuario usuario) {
        usuarioService.updateUsuario(
            id,
            usuario.getUsername(),
            usuario.getClave(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getCorreo(),
            usuario.getTelefono(),
            usuario.getRutaImagen(),
            usuario.getActivo()
        );
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return "redirect:/usuarios";
    }
}

