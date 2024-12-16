package com.DistritoBelleza.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria") // Mapea correctamente el nombre de la columna
    private Long id;

    @Column(name = "tabla_afectada") // Mapea correctamente
    private String tablaAfectada;

    @Column(name = "accion") // Mapea correctamente
    private String accion;

    @Column(name = "datos_afectados", columnDefinition = "CLOB") // Mapea correctamente
    private String datosAfectados;

    @Column(name = "fecha_operacion") // Mapea correctamente
    private LocalDateTime fecha; // Cambia el nombre a fecha_operacion en la base de datos

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTablaAfectada() {
        return tablaAfectada;
    }

    public void setTablaAfectada(String tablaAfectada) {
        this.tablaAfectada = tablaAfectada;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDatosAfectados() {
        return datosAfectados;
    }

    public void setDatosAfectados(String datosAfectados) {
        this.datosAfectados = datosAfectados;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
