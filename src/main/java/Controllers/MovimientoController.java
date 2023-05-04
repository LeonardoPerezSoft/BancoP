package Controllers;

import Entities.Movimiento;
import Exceptions.Mensaje;
import Service.MovimientoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/movimientos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovimientoController {

    @Inject
    MovimientoService movimientoService;

    @GET
    public Response getAllMovimientos() {
        List<Movimiento> movimientos = movimientoService.getAllMovimientos();
        if (movimientos.isEmpty()) {
            String mensajeP = "No ha registrado ningun movimiento";
            Mensaje messageNotify = new Mensaje();
            messageNotify.mensajeExitoso(mensajeP);
            return Response.ok(messageNotify).build();
        }
        return Response.ok(movimientos).build();
    }


    @GET
    @Path("/{id}")
    public Response getMovimientoById(@PathParam("id") Long id) {

        try {
            Movimiento movimiento = movimientoService.getMovimientoById(id);
            if (movimiento == null) {
                String mensajeP = "El Id de movimiento ingresado, no esta resgistrado";
                Mensaje responseMessage = new Mensaje();
                responseMessage.mensajeNotFound(mensajeP);
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(movimiento).build();

        } catch (Exception e) {
            Mensaje messageResponse = new Mensaje();
            messageResponse.setError(e.getCause().getCause().getCause().getMessage());
            return Response.ok(messageResponse).status(Response.Status.BAD_REQUEST).build();
        }
    }


    @POST
    public Response creatMovimiento(Movimiento movimiento) {
        try {
            Movimiento createdMovimiento = movimientoService.crearMovimiento(movimiento);
            return Response.ok(createdMovimiento).status(Response.Status.CREATED).build();
        } catch (Exception e) {

            Mensaje messageResponse = new Mensaje();
            messageResponse.setError(e.getCause().getMessage());
            return Response.ok(messageResponse).status(Response.Status.BAD_REQUEST).build();
        }
    }


    @PUT
    @Path("/{id}")
    public Response updateMovimiento(@PathParam("id") Long id, Movimiento movimiento) {
        try {
            Movimiento c = movimientoService.getMovimientoById(id);
            if (c != null) {
                Movimiento updatedCMovimiento = movimientoService.actualizarMovimiento(id, movimiento);
                String mensajeP = "Movimiento actualizado";
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
    public Response deleteMovimiento(@PathParam("id") Long id) {
        try {
            Movimiento movimiento =  movimientoService.getMovimientoById(id);
            if(movimiento == null){
                String mensajeP = "El Id ingresado, no esta resgistrado";
                Mensaje responseMessage = new Mensaje();
                responseMessage.mensajeNotFound(mensajeP);
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            }
            movimientoService.eliminarMovimiento(id);
            String mensajeP = "Movimiento eliminado!";
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
