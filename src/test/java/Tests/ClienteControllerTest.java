package Tests;

import Controllers.ClienteController;
import Entities.Cliente;
import Exceptions.Mensaje;
import Implements.ClienteRespositoryImp;
import Repository.ClienteRepository;
import Service.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ClienteControllerTest {




    @Inject
    private final ClienteRepository clienteRepositoryMock = Mockito.mock(ClienteRepository.class);

    @Inject
    private final ClienteService clienteService = new ClienteService(clienteRepositoryMock);

    @Inject
    private final ClienteController clienteController = new ClienteController(clienteService);


    @Test
    void getAllClientes() {

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true));

        clientes.add(new Cliente(2l, "Juanita Perez", "Femenino", 18, "1018447000",
                "Avenida Falsa12345", "3114483070", "password", true));

        Mockito.when(clienteRepositoryMock.listAll()).thenReturn(clientes);
        Response response = clienteController.getAllClientes();

        List<Cliente> clientesEsperados = new ArrayList<>();
        clientesEsperados.add(new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true));

        clientesEsperados.add(new Cliente(2l, "Juanita Perez", "Femenino", 18, "1018447000",
                "Avenida Falsa12345", "3114483070", "password", true));

        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(clientesEsperados, response.getEntity());
    }


    @Test
    void getAllClientesFail() {
        List<Cliente> clientes = new ArrayList<>();

        Mockito.when(clienteRepositoryMock.listAll()).thenReturn(clientes);
        Response response = clienteController.getAllClientes();
        Mensaje mensaje = new Mensaje();
        mensaje.mensajeExitoso("No ha registrado ningun cliente!");

        Assertions.assertEquals(mensaje, response.getEntity());
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());



    }

    @Test
    void getClienteById() {

        Cliente cliente = new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true);

         Mockito.when(clienteRepositoryMock.findById(1l))
                .thenReturn(cliente);

        Response response = clienteController.getClienteById(1L);

        Cliente clienteEsperado = new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true);


        Assertions.assertEquals(clienteEsperado, response.getEntity());
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());

    }


    @Test
    void getClienteByIdFail() {



        Mockito.when(clienteRepositoryMock.findById(1l))
                .thenReturn(null);

        Response response = clienteController.getClienteById(1L);


        Mensaje mensaje = new Mensaje();
        mensaje.mensajeNotFound("El Id de cliente ingresado, no esta resgistrado");

        Assertions.assertEquals(mensaje, response.getEntity());
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());


    }


    @Test
    void createCliente() {

        Cliente cliente = new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true);

        Mockito.doNothing().when(clienteRepositoryMock).persist(cliente);

        Response response = clienteController.createCliente(cliente);

        Assertions.assertEquals(HttpStatus.SC_CREATED, response.getStatus());
        Assertions.assertEquals(cliente, response.getEntity());

    }

    @Test
    void updateCliente() {

       Cliente clienteBd = new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true);

       Cliente clienteUpdate = new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
               "Avenida Falsa123", "3114483050", "pasword", true);

        Mockito.when(clienteRepositoryMock.findById(1l)).thenReturn(clienteBd);
        Mockito.doNothing().when(clienteRepositoryMock).persist(clienteUpdate);

        Response response = clienteController.updateCliente(1l, clienteUpdate);


        Mensaje mensaje = new Mensaje();
        mensaje.mensajeExitoso("Cliente actualizado");

        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(mensaje, response.getEntity());

    }

    @Test
    void updateClienteFail() {

        Cliente clienteUpdate = new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true);

        Mockito.when(clienteRepositoryMock.findById(1l)).thenReturn(null);


        Response response = clienteController.updateCliente(1l, clienteUpdate);


        Mensaje mensaje = new Mensaje();
        mensaje.mensajeNotFound("El Id ingresado, no esta resgistrado");

        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
        Assertions.assertEquals(mensaje, response.getEntity());

    }

    @Test
    void deleteCliente() {

        Cliente cliente = new Cliente(1l, "Leonardo Perez", "Masculino", 30, "1018447450",
                "Avenida Falsa123", "3114483050", "pasword", true);

        Mockito.when(clienteRepositoryMock.findById(1l))
                .thenReturn(cliente);

        Mockito.when(clienteRepositoryMock.deleteById(1l)).thenReturn(true);
        Response response = clienteController.deleteCliente(1L);

        Mensaje mensaje = new Mensaje();
        mensaje.mensajeExitoso("Cliente eliminado!");

        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(mensaje, response.getEntity());
    }

    @Test
    void deleteClienteFail() {

        Mockito.when(clienteRepositoryMock.findById(1l))
                .thenReturn(null);

                Response response = clienteController.deleteCliente(1L);

        Mensaje mensaje = new Mensaje();
        mensaje.mensajeNotFound("El Id ingresado, no esta resgistrado");

        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
        Assertions.assertEquals(mensaje, response.getEntity());
    }


}