package com.DistritoBelleza.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gestion_proveedores")
public class GestionProveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGestion;

    private Long idProveedor;
    private Long idMaterial;

    // Getters y Setters
    public Long getIdGestion() {
        return idGestion;
    }

    public void setIdGestion(Long idGestion) {
        this.idGestion = idGestion;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }
}
