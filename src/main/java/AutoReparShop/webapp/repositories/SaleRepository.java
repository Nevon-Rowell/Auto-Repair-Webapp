package AutoReparShop.webapp.repositories;

import AutoReparShop.webapp.models.Sale; // Adjust model import path if needed
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    Optional<Sale> findByJobId(Integer jobId);
}
