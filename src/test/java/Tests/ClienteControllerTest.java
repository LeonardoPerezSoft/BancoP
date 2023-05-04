package Tests;

import Controllers.ClienteController;
import Entities.Cliente;
import Implements.ClienteRespositoryImp;
import Repository.ClienteRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ClienteControllerTest {



    @InjectMock
    ClienteRepository clienteRepository;
//    @Inject
//    ClienteRespositoryImp clienteRespositoryImp;

    @Inject
    ClienteController clienteController;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setGenero("Masculino");
        cliente.setEdad(33);
        cliente.setIdentificacion("1018447486");
        cliente.setTelefono("3114483020");
        cliente.setDireccion("Av falsa 1223");
        cliente.setContrasena("Password");
        cliente.setEstado(true);
        cliente.setId(1L);

    }

    @Test
    void getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        Mockito.when(clienteRepository.listAll()).thenReturn(clientes);
        Response response = clienteController.getAllClientes();
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        List<Cliente> entity = (List<Cliente>) response.getEntity();
        assertFalse(entity.isEmpty());
        assertEquals("Juan Perez", entity.get(0).getNombre());
        assertEquals("Masculino", entity.get(0).getGenero());
        assertEquals(33, entity.get(0).getEdad());
        assertEquals("101847486", entity.get(0).getIdentificacion());
        assertEquals("3114483020", entity.get(0).getTelefono());
        assertEquals("Av falsa 1223", entity.get(0).getDireccion());
        assertEquals("Password", entity.get(0).getContrasena());
        assertEquals(true, entity.get(0).isEstado());
        assertEquals(1L, entity.get(0).getId());
    }

    @Test
    void getClienteById() {
    }

    @Test
    void createCliente() {
    }

    @Test
    void updateCliente() {
    }

    @Test
    void deleteCliente() {
    }
}