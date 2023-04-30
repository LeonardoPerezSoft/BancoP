package Controllers;

import Entities.Cliente;
import Service.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    ClienteService clienteService;

    @GET
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GET
    @Path("/{id}")
    public Response getClienteById(@PathParam("id") Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cliente).build();
    }

    @POST
    public Response createCliente(Cliente cliente) {
        Cliente createdCliente = clienteService.crearCliente(cliente);
        return Response.ok(createdCliente).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCliente(@PathParam("id") Long id, Cliente cliente) {
        Cliente updatedCliente = clienteService.actualizarCliente (id, cliente);
        if (updatedCliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedCliente).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCliente(@PathParam("id") Long id) {
        clienteService.eliminarCliente(id);
        return Response.noContent().build();
    }
}
