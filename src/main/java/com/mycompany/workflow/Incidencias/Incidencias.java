package com.mycompany.workflow.Incidencias;

import java.time.LocalDateTime;

import javax.print.DocFlavor.STRING;

import com.mycompany.workflow.EstructuraUsuarios.Usuario;

public class Incidencias {
    private int id;
    private Usuario asignado;
    private String titulo;
    private String descripcion;
    private Boolean status;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaAtencion;
    private LocalDateTime fechaTermino;


    

    public Incidencias(int id, Usuario asignado, String titulo, String descripcion, Boolean status,
            LocalDateTime fechaInicio, LocalDateTime fechaAtencion, LocalDateTime fechaTermino) {
        this.id = id;
        this.asignado = asignado;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.status = status;
        this.fechaInicio = fechaInicio;
        this.fechaAtencion = fechaAtencion;
        this.fechaTermino = fechaTermino;
    }

    public LocalDateTime getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(LocalDateTime fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public Incidencias() {
        this.fechaInicio= LocalDateTime.now();
        this.status=true;
    }

    public int getId() {
        return id;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDateTime getFechInicio() {
        return fechaInicio;
    }
    public void setFechInicio(LocalDateTime fechInicio) {
        this.fechaInicio = fechInicio;
    }
    public LocalDateTime getFechaTermino() {
        return fechaTermino;
    }
    public void setFechaTermino(LocalDateTime fechaTermino) {
        this.fechaTermino = fechaTermino;
    }
    public Usuario getAsignado() {
        return asignado;
    }
    public void setAsignado(Usuario asignado) {
        this.asignado = asignado;
    }
    
}
