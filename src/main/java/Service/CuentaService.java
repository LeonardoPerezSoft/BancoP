package Service;

import Entities.Cuenta;

import Repository.CuentaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
@ApplicationScoped
public class CuentaService {

    @Inject
    CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    //    @Inject
//    MovimientoService movimientoService;


    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.listAll();
    }

    public Cuenta getCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    @Transactional
    public Cuenta crearCuenta(Cuenta cuenta) {
        cuentaRepository.persist(cuenta);
        return cuenta;
    }

    @Transactional
    public Cuenta actualizarCuenta(Long cuentaId, Cuenta cuenta) {
        Cuenta cuentaExistente = cuentaRepository.findById(cuentaId);
        cuentaExistente.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaExistente.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaExistente.setEstado(cuenta.isEstado());
        cuentaExistente.setCliente(cuenta.getCliente());
        cuentaRepository.persist(cuentaExistente);
        return cuentaExistente;
    }

    @Transactional
    public boolean eliminarCuenta(Long cuentaId) {
        return cuentaRepository.deleteById(cuentaId);
    }


}