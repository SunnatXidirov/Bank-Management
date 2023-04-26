package uz.pdp.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.onlinebanking.entity.CurrencyRate;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate,Integer> {

}
