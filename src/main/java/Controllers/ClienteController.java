package Controllers;

import Entities.Cliente;
import Exceptions.Mensaje;
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

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GET
    public Response getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        if (clientes.isEmpty()) {
            String mensajeP = "No ha registrado ningun cliente!";
            Mensaje messageNotify = new Mensaje();
            messageNotify.mensajeExitoso(mensajeP);
            return Response.ok(messageNotify).build();
        }
        return Response.ok(clientes).build();
    }

    @GET
    @Path("/{id}")
    public Response getClienteById(@PathParam("id") Long id) {

        try {
            Cliente cliente = clienteService.getClienteById(id);
            if (cliente == null) {
                String mensajeP = "El Id de cliente ingresado, no esta resgistrado";
                Mensaje responseMessage = new Mensaje();
                responseMessage.mensajeNotFound(mensajeP);
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            }
           return Response.ok(cliente).build();

        } catch (Exception e) {
            Mensaje messageResponse = new Mensaje();
            messageResponse.setError(e.getCause().getCause().getCause().getMessage());
            return Response.ok(messageResponse).status(Response.Status.BAD_REQUEST).build();
        }
    }


    @POST
    public Response createCliente(Cliente cliente) {
         try {
           Cliente createdCliente = clienteService.crearCliente(cliente);
           return Response.ok(createdCliente).status(Response.Status.CREATED).build();
       } catch (Exception e) {
//       throw new WebApplicationException("Error creando user: " + e.getMessage(), Response.Status.BAD_REQUEST);
           Mensaje messageResponse = new Mensaje();
           messageResponse.setError(e.getCause().getCause().getMessage());
         return Response.ok(messageResponse).status(Response.Status.BAD_REQUEST).build();

                    }
    }

    @PUT
    @Path("/{id}")
    public Response updateCliente(@PathParam("id") Long id, Cliente cliente) {
        try {
            Cliente c = clienteService.getClienteById(id);
            if (c != null) {
                Cliente updatedCliente = clienteService.actualizarCliente (id, cliente);
                String mensajeP = "Cliente actualizado";
                Mensaje messageNotify = new Mensaje();
                messageNotify.mensajeExitoso(mensajeP);
                return Response.ok(messageNotify).build();
            }

            String mensajeP = "El Id ingresado, no esta resgistrado";
            Mensaje responseMessage = new Mensaje();
            responseMessage.mensajeNotFound(mensajeP);
            return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();


        } catch (Exception e) {
            Mensaje messageResponse = new Mensaje();
            messageResponse.setError(e.getCause().getCause().getCause().getMessage());
            return Response.ok(messageResponse).status(Response.Status.BAD_REQUEST).build();
        }

    }

    @DELETE
    @Path("/{id}")
    public Response deleteCliente(@PathParam("id") Long id) {
        try {
           Cliente cliente = clienteService.getClienteById(id);
           if(cliente == null){
               String mensajeP = "El Id ingresado, no esta resgistrado";
               Mensaje responseMessage = new Mensaje();
               responseMessage.mensajeNotFound(mensajeP);
             return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
         }
            clienteService.eliminarCliente(id);
            String mensajeP = "Cliente eliminado!";
            Mensaje messageNotify = new Mensaje();
            messageNotify.mensajeExitoso(mensajeP);
            return Response.ok(messageNotify).build();

        } catch (Exception e) {
            Mensaje messageResponse = new Mensaje();
            messageResponse.setError(e.getCause().getCause().getCause().getMessage());
            return Response.ok(messageResponse).status(Response.Status.BAD_REQUEST).build();
        }

    }
}
