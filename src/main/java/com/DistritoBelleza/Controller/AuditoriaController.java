package com.DistritoBelleza.Controller;

import com.DistritoBelleza.Entity.Auditoria;
import com.DistritoBelleza.Service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/auditoria")
    public String listarAuditoria(Model model) {
        List<Auditoria> auditoria = auditoriaService.getAuditoria();
        model.addAttribute("auditoria", auditoria);
        return "auditoria/lista";
    }
}


