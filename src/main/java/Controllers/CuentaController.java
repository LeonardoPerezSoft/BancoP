package Controllers;


import Entities.Cuenta;
import Exceptions.Mensaje;
import Service.CuentaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cuentas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CuentaController {
    @Inject
    CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GET
    public Response getAllCuentas() {
        List<Cuenta> cuentas = cuentaService.getAllCuentas();
        if (cuentas.isEmpty()) {
            String mensajeP = "No ha registrado ninguna cuenta!";
            Mensaje messageNotify = new Mensaje();
            messageNotify.mensajeExitoso(mensajeP);
            return Response.ok(messageNotify).build();
        }
        return Response.ok(cuentas).build();
    }


    @GET
    @Path("/{id}")
    public Response getCuentaById(@PathParam("id") Long id) {

        try {
            Cuenta cuenta = cuentaService.getCuentaById(id);
            if (cuenta == null) {
                String mensajeP = "El Id de cuenta ingresado, no esta resgistrado";
                Mensaje responseMessage = new Mensaje();
                responseMessage.mensajeNotFound(mensajeP);
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(cuenta).build();

        } catch (Exception e) {
            Mensaje messageResponse = new Mensaje();
            messageResponse.setError(e.getCause().getCause().getCause().getMessage());
            return Response.ok(messageResponse).status(Response.Status.BAD_REQUEST).build();
        }
    }



    @POST
    public Response createCuenta(Cuenta cuenta) {
        try {
            Cuenta createdCuenta = cuentaService.crearCuenta(cuenta);
            System.out.println(createdCuenta);
            return Response.ok(createdCuenta).status(Response.Status.CREATED).build();

        } catch (Exception e) {

            Mensaje messageResponse = new Mensaje();
            messageResponse.setError(e.getCause().getMessage());
            return Response.ok(messageResponse).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCuenta(@PathParam("id") Long id, Cuenta cuenta) {
        try {
            Cuenta c = cuentaService.getCuentaById(id);
            if (c != null) {
                Cuenta updatedCuenta = cuentaService.actualizarCuenta(id, cuenta);
                String mensajeP = "La cuenta fue actualizada!";
                Mensaje messageNotify = new Mensaje();
                messageNotify.mensajeExitoso(mensajeP);
                return Response.ok(messageNotify).build();
            }

            String mensajeP = "El Id ingresado de cuenta ingresado, no esta resgistrado";
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
    public Response deleteCuenta(@PathParam("id") Long id) {
        try {
            Cuenta cuenta = cuentaService.getCuentaById(id);
            if(cuenta == null){
                String mensajeP = "El Id de cuenta ingresado, no esta resgistrado";
                Mensaje responseMessage = new Mensaje();
                responseMessage.mensajeNotFound(mensajeP);
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            }
            cuentaService.eliminarCuenta(id);
            String mensajeP = "La cuenta ha sido eliminada!";
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
