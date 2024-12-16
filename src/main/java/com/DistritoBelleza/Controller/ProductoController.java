package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.Producto;
import com.DistritoBelleza.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Listar todos los productos
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.getProductos());
        return "producto/lista"; // Vista para la lista de productos
    }

    // Mostrar formulario para crear un nuevo producto
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto/formulario"; // Vista para el formulario
    }
    
    

    // Insertar un nuevo producto
    @PostMapping
    public String insertarProducto(@ModelAttribute Producto producto) {
        productoService.insertProducto(
                producto.getIdCategoria(),
                producto.getIdMaterial(),
                producto.getDescripcion(),
                producto.getDetalle(),
                producto.getPrecio(),
                producto.getExistencia(),
                producto.getRutaImagen(),
                producto.getActivo()
        );
        return "redirect:/productos"; // Redirige a la lista después de guardar
    }

    // Mostrar formulario para editar un producto existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductos().stream()
                .filter(p -> p.getIdProducto().equals(id))
                .findFirst()
                .orElse(null);

        if (producto == null) {
            return "error/404"; // Redirige a una página 404 si no se encuentra el producto
        }

        model.addAttribute("producto", producto);
        return "producto/formulario"; // Vista para editar el formulario
    }

    // Actualizar un producto existente
    @PostMapping("/actualizar/{id}")
    public String actualizarProducto(@PathVariable Long id, @ModelAttribute Producto producto) {
        productoService.updateProducto(
                id,
                producto.getIdCategoria(),
                producto.getIdMaterial(),
                producto.getDescripcion(),
                producto.getDetalle(),
                producto.getPrecio(),
                producto.getExistencia(),
                producto.getRutaImagen(),
                producto.getActivo()
        );
        return "redirect:/productos"; // Redirige a la lista después de actualizar
    }

    // Eliminar un producto
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return "redirect:/productos"; // Redirige a la lista después de eliminar
    }


 

@GetMapping("/disponibles")
public String listarProductosDisponibles(Model model) {
    model.addAttribute("productos", productoService.getInventarioDisponible());
    return "producto/disponibles"; // Vista que lista productos disponibles
}


@GetMapping("/listar/promociones-inactivas")
public String listarPromocionesInactivas(Model model) {
    model.addAttribute("promociones", productoService.getPromocionesInactivas());
    return "producto/promociones-inactivas";
}

@GetMapping("/{id:\\d+}")
public String mostrarDetalleProducto(@PathVariable Long id, Model model) {
    Producto producto = productoService.getProductos().stream()
            .filter(p -> p.getIdProducto().equals(id))
            .findFirst()
            .orElse(null);

    if (producto == null) {
        return "error/404"; // Página 404 personalizada si no se encuentra el producto
    }

    model.addAttribute("producto", producto);
    return "producto/detalle";
}

}
