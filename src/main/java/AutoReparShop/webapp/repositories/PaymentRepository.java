package AutoReparShop.webapp.repositories;

<<<<<<< Updated upstream
import AutoReparShop.webapp.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
=======
import com.autoflix.billing.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsBySaleIdAndStatus(Long saleId, String status);
    boolean existsByJobIdAndStatus(Long jobId, String status);
    List<Payment> findAllByOrderByPaymentDateDesc();
>>>>>>> Stashed changes
}