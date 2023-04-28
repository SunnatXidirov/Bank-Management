package uz.pdp.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.onlinebanking.entity.PaymentService;

public interface PaymentServiceRepository extends JpaRepository<PaymentService, Integer> {
}
