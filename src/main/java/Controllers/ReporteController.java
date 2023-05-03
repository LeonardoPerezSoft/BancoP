package Controllers;

import Entities.Reporte;
import Exceptions.Mensaje;
import Service.ReporteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

@Path("/reportes")
@Produces(MediaType.APPLICATION_JSON)
public class ReporteController {

    @Inject
    ReporteService reporteService;

    @GET
    public Response genarateReporte(@QueryParam("fechaInicio") String fechaInicio,
                                    @QueryParam("fechaFin") String fechaFin,
                                    @QueryParam("idCliente") Long idCliente) {
        try {
            LocalDate fechaInicio1 = LocalDate.parse(fechaInicio);
            LocalDate fechaFin1 = LocalDate.parse(fechaFin);
            List<Reporte> report = reporteService.generateReporte(fechaInicio1, fechaFin1, idCliente);
            return Response.ok(report).build();

        } catch (Exception e) {
            Mensaje messageResponse = new Mensaje();
            messageResponse.buildMessageBdError(e.getMessage());
            return Response.ok(messageResponse).status(Response.Status.BAD_REQUEST).build();
        }
        }
    }
