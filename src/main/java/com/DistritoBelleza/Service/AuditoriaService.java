package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.Auditoria;
import com.DistritoBelleza.Repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    public List<Auditoria> getAuditoria() {
        return auditoriaRepository.getAuditoria();
    }
}
