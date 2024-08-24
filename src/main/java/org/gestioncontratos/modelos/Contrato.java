package org.gestioncontratos.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El Nombre es necesario")
    private String nombre;

    @ManyToOne
    @JoinColumn (name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn (name = "servicio_id")
    private Servicio servicio;

    @NotBlank (message = "El tiempo es necesario")
    private String tiempo;

    @NotBlank (message = "La fecha de inicio es necesaria")
    private String fechaInicio;

    @NotBlank (message = "La fecha de finalizacion es necesaria")
    private String fechaFinal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "El Nombre es necesario") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El Nombre es necesario") String nombre) {
        this.nombre = nombre;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public @NotBlank(message = "El tiempo es necesario") String getTiempo() {
        return tiempo;
    }

    public void setTiempo(@NotBlank(message = "El tiempo es necesario") String tiempo) {
        this.tiempo = tiempo;
    }

    public @NotBlank(message = "La fecha de inicio es necesaria") String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(@NotBlank(message = "La fecha de inicio es necesaria") String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public @NotBlank(message = "La fecha de finalizacion es necesaria") String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(@NotBlank(message = "La fecha de finalizacion es necesaria") String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
