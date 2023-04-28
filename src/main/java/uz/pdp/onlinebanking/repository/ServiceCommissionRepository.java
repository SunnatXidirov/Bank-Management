package uz.pdp.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.onlinebanking.entity.ServiceCommission;

import java.util.List;

public interface ServiceCommissionRepository extends JpaRepository<ServiceCommission, Integer> {
    List<ServiceCommission> findAllByActiveTrue();
    ServiceCommission findByIdAndActiveTrue(Integer id);
}
