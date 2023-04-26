package uz.pdp.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.onlinebanking.entity.PaymentCategory;

public interface PaymentCategoryRepository extends JpaRepository<PaymentCategory, Integer> {

}
