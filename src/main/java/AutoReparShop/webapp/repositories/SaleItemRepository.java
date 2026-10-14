package AutoReparShop.webapp.repositories;

import com.autoflix.billing.model.SaleItem;
import com.autoflix.billing.model.SaleItemId; // Adjust import if SaleItemId is in a different package
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SaleItemRepository extends JpaRepository<SaleItem, SaleItemId> {
    List<SaleItem> findBySaleId(Long saleId);
}