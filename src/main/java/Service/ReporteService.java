package Service;


import Entities.Reporte;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ReporteService {
    @PersistenceContext
    EntityManager manager;

    public List<Reporte> generateReporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente) {
        String query = "SELECT "
                + "mv.fecha fecha, "
                + "cl.nombre nombre, "
                + "ct.numerocuenta numerocuenta, "
                + "ct.tipocuenta tipo, "
                + "mv.saldo saldoInicial, "
                + "ct.estado estado, "
                + "mv.valor movimiento, "
                + "ct.saldoinicial saldoDisponible, "
                + "mv.tipomovimiento "
                + "FROM cliente cl "
                + "join cuenta ct "
                + "on cl.id = ct.cliente_id "
                + "join movimiento mv "
                + "on ct.id = mv.cuenta_id "
                + "WHERE cl.id = :idCliente "
                + "AND mv.fecha between :fechaInicio AND :fechaFin";

        List<Object[]> resultados = manager.createNativeQuery(query)
                .setParameter("idCliente", idCliente)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();

        List<Reporte> reportes = new ArrayList<>();

        for (Object[] resultado : resultados) {
            Reporte reporte = new Reporte();
            float valor_movimiento = tipoMovimiento(resultado);

            reporte.setFecha((Date) resultado[0]);
            reporte.setCliente((String) resultado[1]);
            reporte.setNumeroCuenta((String) resultado[2]);
            reporte.setTipoCuenta((String) resultado[3]);
            reporte.setSaldoInicial((float) resultado[4]);
            reporte.setEstado((boolean) resultado[5]);
            reporte.setMovimiento(valor_movimiento);
            reporte.setSaldoDisponible((float) resultado[7]);
            reportes.add(reporte);
        }
        return reportes;
    }

    private float tipoMovimiento(Object[] resultado) {
        String tipo_movimiento = (String) resultado[8];
        float valor_movimiento = (float) resultado[6];
        if (tipo_movimiento.equalsIgnoreCase("Retiro"))
            valor_movimiento = valor_movimiento * -1;
        return valor_movimiento;
    }
}
