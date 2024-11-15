package com.DistritoBelleza.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seguimiento_promociones")
public class SeguimientoPromociones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromociones;

    private Long idProducto;
    private String promocion;
    private Integer duracion;

    // Getters y Setters
    public Long getIdPromociones() {
        return idPromociones;
    }

    public void setIdPromociones(Long idPromociones) {
        this.idPromociones = idPromociones;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
}

