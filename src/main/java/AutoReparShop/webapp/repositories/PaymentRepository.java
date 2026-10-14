package AutoReparShop.webapp.repositories;

import AutoReparShop.webapp.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}