package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccesoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/acceso")
    public String acceso(@RequestParam(value = "query", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            model.addAttribute("productos", productoService.buscarProductos(query));
        }
        return "acceso/lista";
    }
}



