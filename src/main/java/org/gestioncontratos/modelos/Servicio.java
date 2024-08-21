package org.gestioncontratos.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El Nombre es necesario")
    private String nombre;

    @NotBlank (message = "La descripcion es necesario")
    private String descripcion;

    @NotBlank (message = "El tiempo es necesario")
    private String tiempo;

    @NotBlank (message = "El precio es necesario")
    private String precio;

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

    public @NotBlank(message = "La descripcion es necesario") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank(message = "La descripcion es necesario") String descripcion) {
        this.descripcion = descripcion;
    }

    public @NotBlank(message = "El tiempo es necesario") String getTiempo() {
        return tiempo;
    }

    public void setTiempo(@NotBlank(message = "El tiempo es necesario") String tiempo) {
        this.tiempo = tiempo;
    }

    public @NotBlank(message = "El precio es necesario") String getPrecio() {
        return precio;
    }

    public void setPrecio(@NotBlank(message = "El precio es necesario") String precio) {
        this.precio = precio;
    }
}
