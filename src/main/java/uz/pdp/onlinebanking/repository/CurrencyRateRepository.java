package uz.pdp.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.onlinebanking.entity.Currency;
import uz.pdp.onlinebanking.entity.CurrencyRate;
import uz.pdp.onlinebanking.entity.enums.ActionType;

import java.util.List;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate,Integer> {
    List<CurrencyRate> findAllByActiveTrue();

    CurrencyRate findByCurrency_Id(Integer currency_id);

    CurrencyRate findByCurrencyAndActionTypeAndActiveTrue(Currency currency, ActionType actionType);

}
