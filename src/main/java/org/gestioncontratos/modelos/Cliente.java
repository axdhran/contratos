package org.gestioncontratos.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank (message = "El Nombre es necesario")
    private String nombre;

    @NotBlank (message = "El Apellido es necesario")
    private String apellido;

    @NotBlank (message = "El DUI es necesario")
    private String dui;

    @NotBlank (message = "El telefono es necesario")
    private String telefono;

    @NotBlank (message = "El Direccion es necesario")
    private String direccion;

    @NotBlank (message = "El Nombre es necesario")
    private String correo;

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

    public @NotBlank(message = "El Apellido es necesario") String getApellido() {
        return apellido;
    }

    public void setApellido(@NotBlank(message = "El Apellido es necesario") String apellido) {
        this.apellido = apellido;
    }

    public @NotBlank(message = "El DUI es necesario") String getDui() {
        return dui;
    }

    public void setDui(@NotBlank(message = "El DUI es necesario") String dui) {
        this.dui = dui;
    }

    public @NotBlank(message = "El telefono es necesario") String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotBlank(message = "El telefono es necesario") String telefono) {
        this.telefono = telefono;
    }

    public @NotBlank(message = "El Direccion es necesario") String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotBlank(message = "El Direccion es necesario") String direccion) {
        this.direccion = direccion;
    }

    public @NotBlank(message = "El Nombre es necesario") String getCorreo() {
        return correo;
    }

    public void setCorreo(@NotBlank(message = "El Nombre es necesario") String correo) {
        this.correo = correo;
    }
}
