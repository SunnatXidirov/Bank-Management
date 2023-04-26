package uz.pdp.onlinebanking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onlinebanking.entity.Currency;
import uz.pdp.onlinebanking.payload.CurrencyDto;
import uz.pdp.onlinebanking.payload.Respons;
import uz.pdp.onlinebanking.repository.CurrencyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversionCurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ObjectMapper objectMapper;

    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

    public Respons getCurrencyById(Integer id) {
        Optional<Currency> currency = currencyRepository.findById(id);
        if (currency.isPresent()) {
            Currency currency1 = currency.get();
            if (currency1.isActive()) {
                return new Respons("succesful", true, currency1);
            } else {
                return new Respons("Xato id", false, new Respons());
            }
        }
        return new Respons("bu idli topilmadi", false, new Respons());
    }

    @Transactional
    public Respons addOrUpdateCurrency(List<CurrencyDto> dtos) {
        List<Currency> currencyList = dtos.stream().map(item
                -> objectMapper.convertValue(item, Currency.class)).collect(Collectors.toList());
        currencyRepository.saveAll(currencyList);
        return new Respons("succesful", true, new Respons());
    }


}
