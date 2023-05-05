package Tests;

import Controllers.ClienteController;
import Controllers.CuentaController;
import Entities.Cliente;
import Entities.Cuenta;
import Exceptions.Mensaje;
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
    void getAllCuentasFail() {

        List<Cuenta> cuentas = new ArrayList<>();

        Mockito.when(cuentaRepositoryMock.listAll()).thenReturn(cuentas);
        Response response = cuentaController.getAllCuentas();

        Mensaje mensaje = new Mensaje();
        mensaje.mensajeExitoso("No ha registrado ninguna cuenta!");

        Assertions.assertEquals(mensaje, response.getEntity());
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());


    }

    @Test
    void getCuentaById() {

        Cuenta cuenta = new Cuenta(1l, "456789", "Ahorros", 500,
                true, new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true));

        Mockito.when(cuentaRepositoryMock.findById(1l))
                .thenReturn(cuenta);
        Response response = cuentaController.getCuentaById(1l);

        Cuenta cuentaEsperada = new Cuenta(1l, "456789", "Ahorros", 500,
                true, new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true));

        Assertions.assertEquals(cuentaEsperada, response.getEntity());
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());

    }

    @Test
    void getCuentaByIdFail() {


        Mockito.when(cuentaRepositoryMock.findById(1l))
                .thenReturn(null);
        Response response = cuentaController.getCuentaById(1l);

       Mensaje mensaje = new Mensaje();
       mensaje.mensajeNotFound("El Id de cuenta ingresado, no esta resgistrado");

        Assertions.assertEquals(mensaje, response.getEntity());
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());

    }

    @Test
    void createCuenta() {

        Cuenta cuenta = new Cuenta(1l, "456789", "Ahorros", 500,
                true, new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true));

        Mockito.doNothing().when(cuentaRepositoryMock).persist(cuenta);
        Response response = cuentaController.createCuenta(cuenta);

        Assertions.assertEquals(HttpStatus.SC_CREATED, response.getStatus());
        Assertions.assertEquals(cuenta, response.getEntity());


    }

    @Test
    void updateCuenta() {

        Cuenta cuentaBd = new Cuenta(1l, "456789", "Ahorros", 500,
                true, new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true));

        Cuenta cuentaUpdate = new Cuenta(1l, "456789", "Ahorros", 500,
                true, new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true));

        Mockito.when(cuentaRepositoryMock.findById(1l)).thenReturn(cuentaBd);
        Mockito.doNothing().when(cuentaRepositoryMock).persist(cuentaUpdate);

        Response response = cuentaController.updateCuenta(1l, cuentaUpdate);

        Mensaje mensaje = new Mensaje();
        mensaje.mensajeExitoso("La cuenta fue actualizada!");


        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(mensaje, response.getEntity());

    }

    @Test
    void updateCuentaFail() {

        Cuenta cuentaUpdate = new Cuenta(1l, "456789", "Ahorros", 500,
                true, new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true));

        Mockito.when(cuentaRepositoryMock.findById(1l)).thenReturn(null);


        Response response = cuentaController.updateCuenta(1l, cuentaUpdate);

        Mensaje mensaje = new Mensaje();
        mensaje.mensajeNotFound("El Id ingresado de cuenta ingresado, no esta resgistrado");


        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
        Assertions.assertEquals(mensaje, response.getEntity());

    }


    @Test
    void deleteCuenta() {

        Cuenta cuenta = new Cuenta(1l, "456789", "Ahorros", 500,
                true, new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true));

        Mockito.when(cuentaRepositoryMock.findById(1l))
                        .thenReturn(cuenta);

        Mockito.when(cuentaRepositoryMock.deleteById(1l)).thenReturn(true);
        Response response = cuentaController.deleteCuenta(1l);

        Mensaje mensaje = new Mensaje();
        mensaje.mensajeExitoso("La cuenta ha sido eliminada!");

        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(mensaje, response.getEntity());

    }

    @Test
    void deleteCuentaFail() {

                Mockito.when(cuentaRepositoryMock.findById(1l))
                .thenReturn(null);


        Response response = cuentaController.deleteCuenta(1l);

        Mensaje mensaje = new Mensaje();
        mensaje.mensajeNotFound("El Id de cuenta ingresado, no esta resgistrado");

        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
        Assertions.assertEquals(mensaje, response.getEntity());

    }


}