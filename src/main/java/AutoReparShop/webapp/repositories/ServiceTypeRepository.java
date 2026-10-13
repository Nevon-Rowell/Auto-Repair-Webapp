package AutoReparShop.webapp.repositories;

import AutoReparShop.webapp.models.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
}