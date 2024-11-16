package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.GestionCliente;
import com.DistritoBelleza.Service.GestionClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class GestionClienteController {

    @Autowired
    private GestionClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.getClientes());
        return "clientes/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new GestionCliente());
        return "clientes/formulario";
    }

    @PostMapping
    public String insertCliente(@ModelAttribute GestionCliente cliente) {
        clienteService.insertCliente(
            cliente.getIdUsuario(),
            cliente.getNombre(),
            cliente.getNumeroFactura(),
            cliente.getCorreo()
        );
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", clienteService.getClientes()
            .stream()
            .filter(c -> c.getIdCliente().equals(id))
            .findFirst()
            .orElse(null));
        return "clientes/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String updateCliente(@PathVariable Long id, @ModelAttribute GestionCliente cliente) {
        clienteService.updateCliente(
            id,
            cliente.getIdUsuario(),
            cliente.getNombre(),
            cliente.getNumeroFactura(),
            cliente.getCorreo()
        );
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return "redirect:/clientes";
    }
}
