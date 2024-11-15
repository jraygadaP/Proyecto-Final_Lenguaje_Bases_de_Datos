package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.InventarioMateriales;
import com.DistritoBelleza.Service.InventarioMaterialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventario")
public class InventarioMaterialesController {

    @Autowired
    private InventarioMaterialesService inventarioMaterialesService;

    @GetMapping
    public String listarInventario(Model model) {
        model.addAttribute("inventario", inventarioMaterialesService.getInventarioMateriales());
        return "inventario/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoMaterial(Model model) {
        model.addAttribute("material", new InventarioMateriales());
        return "inventario/formulario";
    }

    @PostMapping
    public String insertInventarioMateriales(@ModelAttribute InventarioMateriales material) {
        inventarioMaterialesService.insertInventarioMateriales(material.getTipoMaterial(), material.getCantidad());
        return "redirect:/inventario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarMaterial(@PathVariable Long id, Model model) {
        model.addAttribute("material", inventarioMaterialesService.getInventarioMateriales().stream().filter(m -> m.getIdMaterial().equals(id)).findFirst().orElse(null));
        return "inventario/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String updateInventarioMateriales(@PathVariable Long id, @ModelAttribute InventarioMateriales material) {
        inventarioMaterialesService.updateInventarioMateriales(id, material.getTipoMaterial(), material.getCantidad());
        return "redirect:/inventario";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteInventarioMateriales(@PathVariable Long id) {
        inventarioMaterialesService.deleteInventarioMateriales(id);
        return "redirect:/inventario";
    }
    
    @GetMapping("/{id}")
    public String mostrarDetalleMaterial(@PathVariable Long id, Model model) {
        InventarioMateriales material = inventarioMaterialesService.getInventarioMateriales().stream()
                .filter(m -> m.getIdMaterial().equals(id))
                .findFirst()
                .orElse(null);
        if (material == null) {
            return "error/404";
        }
        model.addAttribute("material", material);
        return "inventario/detalle";
    }
}
