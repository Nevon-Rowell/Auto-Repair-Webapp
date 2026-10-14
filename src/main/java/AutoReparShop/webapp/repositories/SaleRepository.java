package AutoReparShop.webapp.repositories;

import AutoReparShop.webapp.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    Optional<Sale> findByJobID(Integer jobID);
}