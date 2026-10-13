package AutoReparShop.webapp.repositories;

import AutoReparShop.webapp.models.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, Integer> {
    List<SparePart> findByIsActiveTrue();
}