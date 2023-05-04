package Tests;

import Entities.Cuenta;
import Service.CuentaService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CuentaControllerTest {

    @Inject
    CuentaService cuentaService;





    @Test
    void getAllCuentas() {
       List<Cuenta> cuentaList = cuentaService.getAllCuentas();
       assertFalse(cuentaList.isEmpty());
       //assertEquals(cuentaList.size(), 5);
      // assertEquals(cuentaList.get(0).getTipoCuenta(), "Ahorro");

    }

    @Test
    void getCuentaById() {
    }

    @Test
    void createCuenta() {
    }

    @Test
    void updateCuenta() {
    }

    @Test
    void deleteCuenta() {
    }
}