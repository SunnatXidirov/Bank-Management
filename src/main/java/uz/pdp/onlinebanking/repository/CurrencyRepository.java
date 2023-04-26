package uz.pdp.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.onlinebanking.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    boolean existsByAbbrAndName(String abbr, String name);

}
