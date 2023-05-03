package Repository;

import Entities.Cuenta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CuentaRepository extends PanacheRepository<Cuenta> {
}
