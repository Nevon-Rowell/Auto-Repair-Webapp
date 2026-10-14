package AutoReparShop.webapp.repositories;

<<<<<<< Updated upstream
import AutoReparShop.webapp.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    Optional<Sale> findByJobID(Integer jobID);
}
=======
public interface SaleRepository {package com.autoflix.billing.repo;

import com.autoflix.billing.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface SaleRepository extends JpaRepository<Sale, Long> {}

}
>>>>>>> Stashed changes
