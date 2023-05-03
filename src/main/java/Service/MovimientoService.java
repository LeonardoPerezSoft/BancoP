package Service;

import Entities.Cuenta;
import Entities.Movimiento;
import Repository.CuentaRepository;
import Repository.MovimientoRepository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class MovimientoService implements PanacheRepository<Movimiento> {

    @Inject
    MovimientoRepository movimientoRepository;

    @Inject
    CuentaService cuentaService;


    public List<Movimiento> getAllMovimientos(){
        return movimientoRepository.listAll();
    }

    public Movimiento getMovimientoById(Long id){
        return movimientoRepository.findById(id);
    }

    @Transactional
    public Movimiento crearMovimiento(Movimiento movimiento){
         actualizar_cuenta(movimiento);

         movimientoRepository.persist(movimiento);
        return movimiento;

    }

    @Transactional
    public Movimiento actualizarMovimiento(Long movimientoId, Movimiento movimiento){
        actualizar_cuenta(movimiento);

        Movimiento movimientoExistente = movimientoRepository.findById(movimientoId);
        movimientoExistente.setFecha(movimiento.getFecha());
        movimientoExistente.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoExistente.setValor(movimiento.getValor());
        movimientoExistente.setSaldo(movimiento.getSaldo());
        movimientoRepository.persist(movimientoExistente);
        return movimientoExistente;
    }

    @Transactional
    public boolean eliminarMovimiento(Long movimientoId){
        return movimientoRepository.deleteById(movimientoId);

    }

    private void actualizar_cuenta(Movimiento movimiento) {
        Long id_cuenta = movimiento.getCuenta().getId();
        Cuenta cuentaAsociada = cuentaService.getCuentaById(id_cuenta);

        if (cuentaAsociada != null) {
            float saldoInicial = cuentaAsociada.getSaldoInicial();
            String tipoMovimiento = movimiento.getTipoMovimiento();

            movimiento.setSaldo(saldoInicial);

            switch (tipoMovimiento) {
                case "Retiro":
                    if (saldoDisponible(movimiento, saldoInicial))
                        cuentaAsociada.setSaldoInicial(saldoInicial - movimiento.getValor());
                    else
                        throw new IllegalArgumentException(
                                "ERROR: Saldo no disponible. Detail: Tiene un saldo de " + saldoInicial
                                        + ", insuficiente para realizar el retiro");
                    break;
                case "Deposito":
                    cuentaAsociada.setSaldoInicial(saldoInicial + movimiento.getValor());
                    break;
            }
            cuentaService.actualizarCuenta(id_cuenta, cuentaAsociada);
        }
    }


    private boolean saldoDisponible(Movimiento movimiento, float saldoInicial) {
        if (saldoInicial == 0.0 || (saldoInicial - movimiento.getValor()) < 0.0)
            return false;
        return true;
    }



//    @Transactional
//    public Movimiento crearMovimiento(Movimiento movimiento) {
//        movimientoRepository.persist(movimiento);
//        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId());
//        BigDecimal nuevoSaldo = cuenta.getSaldoActual().add(movimiento.getValor());
//        cuenta.setSaldoInicial(nuevoSaldo);
//        cuentaRepository.persist(cuenta);
//        return movimiento;
//    }
//
//    public List<Movimiento> obtenerMovimientos(Long cuentaId, Date fechaInicio, Date fechaFin) {
//        return movimientoRepository.findBetweenDates(cuentaId, fechaInicio, fechaFin);
//    }





}
