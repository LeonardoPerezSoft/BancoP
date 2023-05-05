package Tests;

import Controllers.ClienteController;
import Controllers.CuentaController;
import Entities.Cliente;
import Entities.Cuenta;
import Repository.ClienteRepository;
import Repository.CuentaRepository;
import Service.ClienteService;
import Service.CuentaService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CuentaControllerTest {

    @Inject
    private final CuentaRepository cuentaRepositoryMock = Mockito.mock(CuentaRepository.class);

    @Inject
    private final CuentaService  cuentaService = new CuentaService(cuentaRepositoryMock);

    @Inject
    private final CuentaController cuentaController = new CuentaController(cuentaService);


    @Test
    void getAllCuentas() {

        List<Cuenta> cuentas = new ArrayList<>();
        cuentas.add(new Cuenta(1l, "456789", "Ahorros", 500,
                true, new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true)));

        cuentas.add(new Cuenta(2l, "123456", "Corriente",
                500,true, new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true)));

           Mockito.when(cuentaRepositoryMock.listAll()).thenReturn(cuentas);
           Response response = cuentaController.getAllCuentas();


        List<Cuenta> cuentasEsperadas = new ArrayList<>();
        cuentasEsperadas.add(new Cuenta(1l, "456789", "Ahorros", 500,
                true, new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true)));

        cuentasEsperadas.add(new Cuenta(2l, "123456", "Corriente",
                500,true, new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true)));

        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(cuentasEsperadas, response.getEntity());


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