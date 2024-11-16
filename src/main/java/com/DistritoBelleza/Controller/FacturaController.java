package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.Factura;
import com.DistritoBelleza.Service.FacturaService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public String listarFacturas(Model model) {
        model.addAttribute("facturas", facturaService.getFacturas());
        return "facturas/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaFactura(Model model) {
        model.addAttribute("factura", new Factura());
        return "facturas/formulario";
    }

@PostMapping
public String insertFactura(@ModelAttribute Factura factura) {
    // Validaciones de campos
    if (factura.getIdEmpleado() == null || 
        factura.getIdUsuario() == null || 
        factura.getIdProducto() == null || 
        factura.getFechaEmision() == null || 
        factura.getDetalleServicio() == null) {
        throw new IllegalArgumentException("Todos los campos son obligatorios.");
    }

     // Obtener la fecha proporcionada como String
    String fechaInput = factura.getFechaEmision();

    // Convertir la fecha de String a LocalDate (sin tiempo)
    LocalDate fecha = LocalDate.parse(fechaInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    // Convertir LocalDate a LocalDateTime estableciendo la hora inicial
    LocalDateTime fechaEmision = fecha.atStartOfDay();

    facturaService.insertFactura(
        factura.getNumeroFactura(),
        factura.getIdEmpleado(),
        factura.getIdUsuario(),
        factura.getIdProducto(),
        fechaEmision,
        factura.getDetalleServicio()
    );

    return "redirect:/facturas";
}



    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarFactura(@PathVariable Long id, Model model) {
        model.addAttribute("factura", facturaService.getFacturas()
                .stream()
                .filter(f -> f.getNumeroFactura().equals(id))
                .findFirst()
                .orElse(null));
        return "facturas/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String updateFactura(@PathVariable Long id, @ModelAttribute Factura factura) {
        facturaService.updateFactura(
                id,
                factura.getIdEmpleado(),
                factura.getIdUsuario(),
                factura.getIdProducto(),
                factura.getFechaEmision(),
                factura.getDetalleServicio()
        );
        return "redirect:/facturas";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteFactura(@PathVariable Long id) {
        facturaService.deleteFactura(id);
        return "redirect:/facturas";
    }
}
