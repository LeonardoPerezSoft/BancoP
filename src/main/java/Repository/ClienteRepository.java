package Repository;

import Entities.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ClienteRepository extends PanacheRepository<Cliente> {
}