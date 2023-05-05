package Entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true)
    private String numeroCuenta;
    private String tipoCuenta;
    private float saldoInicial;
    private boolean estado;


    public Cuenta() {
    }

    public Cuenta(Long id, String numeroCuenta, String tipoCuenta, float saldoInicial, boolean estado, Cliente cliente) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.cliente = cliente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "cuenta_fk"))
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public float getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(float saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Float.compare(cuenta.saldoInicial, saldoInicial) == 0 && estado == cuenta.estado && Objects.equals(id, cuenta.id) && Objects.equals(numeroCuenta, cuenta.numeroCuenta) && Objects.equals(tipoCuenta, cuenta.tipoCuenta) && Objects.equals(cliente, cuenta.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroCuenta, tipoCuenta, saldoInicial, estado, cliente);
    }
}





