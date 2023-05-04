package Entities;

import io.quarkus.agroal.DataSource;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persona {



    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String nombre;

    @Check(constraints = "genero = 'Masculino' OR genero = 'Femenino'")
    private String genero;
    private int edad;
    @Column(unique = true)
    private String identificacion;

    private String direccion;
    private String telefono;


    public Persona() {
    }

    public Persona(Long id, String nombre, String genero, int edad, String identificacion, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return edad == persona.edad && Objects.equals(id, persona.id) && Objects.equals(nombre, persona.nombre) &&
                Objects.equals(genero, persona.genero) && Objects.equals(identificacion, persona.identificacion) &&
                Objects.equals(direccion, persona.direccion) && Objects.equals(telefono, persona.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, genero, edad, identificacion, direccion, telefono);
    }
}
