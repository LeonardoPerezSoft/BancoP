package Service;

import Entities.Cliente;
import Entities.Persona;
import Repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.listAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente crearCliente(Cliente cliente) {
        clienteRepository.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente actualizarCliente(Long clienteId, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(clienteId);
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setGenero(cliente.getGenero());
        clienteExistente.setEdad(cliente.getEdad());
        clienteExistente.setIdentificacion(cliente.getIdentificacion());
        clienteExistente.setDireccion(cliente.getDireccion());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setContrasena(cliente.getContrasena());
        clienteExistente.setEstado(cliente.getEstado());
        clienteRepository.persist(clienteExistente);
        return clienteExistente;
    }

    @Transactional
    public boolean eliminarCliente(Long clienteId) {
        return clienteRepository.deleteById(clienteId);
    }
}