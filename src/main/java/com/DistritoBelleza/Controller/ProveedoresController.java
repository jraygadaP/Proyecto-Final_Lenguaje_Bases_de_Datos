package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.Proveedores;
import com.DistritoBelleza.Service.ProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedores")
public class ProveedoresController {

    @Autowired
    private ProveedoresService proveedoresService;

    @GetMapping
    public String listarProveedores(Model model) {
        model.addAttribute("proveedores", proveedoresService.getProveedores());
        return "proveedor/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedores());
        return "proveedor/formulario";
    }

    @PostMapping
    public String insertarProveedor(@ModelAttribute Proveedores proveedor) {
        proveedoresService.insertProveedor(proveedor.getNombre());
        return "redirect:/proveedores";
    }

    @GetMapping("/{idProveedor}")
    public String mostrarDetalleProveedor(@PathVariable Long idProveedor, Model model) {
        Proveedores proveedor = proveedoresService.getProveedores().stream()
                .filter(p -> p.getIdProveedor().equals(idProveedor))
                .findFirst()
                .orElse(null);

        if (proveedor == null) {
            return "error/404";
        }

        model.addAttribute("proveedor", proveedor);
        return "proveedor/detalle";
    }
}
