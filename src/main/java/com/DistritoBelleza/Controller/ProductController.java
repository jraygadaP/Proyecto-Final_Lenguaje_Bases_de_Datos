/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.Product;
import com.DistritoBelleza.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entidades")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listarEntidades(Model model) {
        model.addAttribute("entidades", productService.getProducts());
        return "product/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaEntidad(Model model) {
        model.addAttribute("entidad", new Product());
        return "product/formulario";
    }

    @PostMapping
    public String insertProduct(@ModelAttribute Product entidad) {
        productService.insertProduct(entidad.getName(), entidad.getPrice());
        return "redirect:/entidades";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarEntidad(@PathVariable Long id, Model model) {
        model.addAttribute("entidad", productService.getProducts().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null));
        return "product/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product entidad) {
        productService.updateProduct(id, entidad.getName(), entidad.getPrice());
        return "redirect:/entidades";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/entidades";
    }
    
@GetMapping("/{id}")
public String mostrarEntidad(@PathVariable Long id, Model model) {
    Product entidad = productService.getProducts().stream()
                                    .filter(p -> p.getId().equals(id))
                                    .findFirst()
                                    .orElse(null);
    if (entidad == null) {
        return "error/404";  // Redirige a una página 404 personalizada si no se encuentra
    }
    model.addAttribute("entidad", entidad);
    return "product/detalle";  // Nombre de la plantilla
}

}
