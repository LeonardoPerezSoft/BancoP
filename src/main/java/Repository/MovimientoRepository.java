package Repository;

import Entities.Movimiento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.swing.*;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public interface MovimientoRepository extends PanacheRepository<Movimiento> {
}



//public class MovimientoRepository {
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    public List<Movimiento> findBetweenDates(Long cuentaId, Date fechaInicio, Date fechaFin) {
//        return entityManager.createQuery(
//                        "SELECT m FROM Movimiento m WHERE m.cuenta.id = :cuentaId AND m.fecha BETWEEN :fechaInicio AND :fechaFin", Movimiento.class)
//                .setParameter("cuentaId", cuentaId)
//                .setParameter("fechaInicio", fechaInicio)
//                .setParameter("fechaFin", fechaFin)
//                .getResultList();
//    }
//}