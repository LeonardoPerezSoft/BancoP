package Entities;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Cliente extends Persona {

    private String contrasena;

    private boolean estado;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String genero, int edad, String identificacion, String direccion, String telefono, String contrasena, boolean estado) {
        super(id, nombre, genero, edad, identificacion, direccion, telefono);
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return estado == cliente.estado && Objects.equals(contrasena, cliente.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contrasena, estado);
    }
}



