package AutoReparShop.webapp.repositories;

import AutoReparShop.webapp.models.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, Integer> {

}