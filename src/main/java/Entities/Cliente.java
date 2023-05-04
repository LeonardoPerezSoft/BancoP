package Entities;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Persona {

    private String contrasena;

    private boolean estado;

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
}



