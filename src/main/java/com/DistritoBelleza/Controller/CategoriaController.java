package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.Categoria;
import com.DistritoBelleza.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "categoria/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/formulario";
    }

    @PostMapping
    public String insertCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.insertCategoria(categoria.getDescripcion(), categoria.getRutaImagen(), categoria.getActivo());
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCategoria(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.getCategorias().stream().filter(c -> c.getIdCategoria().equals(id)).findFirst().orElse(null));
        return "categoria/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String updateCategoria(@PathVariable Long id, @ModelAttribute Categoria categoria) {
        categoriaService.updateCategoria(id, categoria.getDescripcion(), categoria.getRutaImagen(), categoria.getActivo());
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return "redirect:/categorias";
    }
    
    @GetMapping("/{id}")
public String mostrarDetalleCategoria(@PathVariable Long id, Model model) {
    Categoria categoria = categoriaService.getCategorias().stream()
            .filter(c -> c.getIdCategoria().equals(id))
            .findFirst()
            .orElse(null);
    if (categoria == null) {
        return "error/404"; // Redirige a una página 404 personalizada si no se encuentra
    }
    model.addAttribute("categoria", categoria);
    return "categoria/detalle"; // Nombre de la plantilla de detalle
}

}
