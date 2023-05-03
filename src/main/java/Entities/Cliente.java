package Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Persona {


    private String contrasena;
    private boolean estado;

//    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Cuenta> cuentas = new ArrayList<>();


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


}
