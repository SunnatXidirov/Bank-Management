package uz.pdp.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.onlinebanking.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
