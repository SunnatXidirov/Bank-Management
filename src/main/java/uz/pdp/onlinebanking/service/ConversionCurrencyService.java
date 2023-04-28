package uz.pdp.onlinebanking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onlinebanking.entity.Currency;
import uz.pdp.onlinebanking.payload.ConversionPayload.CurrencyDtoForPost;
import uz.pdp.onlinebanking.payload.ConversionPayload.CurrencyDtoForPut;
import uz.pdp.onlinebanking.payload.ConversionPayload.Respons;
import uz.pdp.onlinebanking.repository.CurrencyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversionCurrencyService {

    private final CurrencyRepository currencyRepositorys;
    private final ObjectMapper objectMapper;

    public List<Currency> getAllCurrency() {
        return currencyRepositorys.findAll();
    }

    public Respons getCurrencyById(Integer id) {
        Optional<Currency> currency = currencyRepositorys.findById(id);
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
    public Respons addCurrency(List<CurrencyDtoForPost> dtos) {
        List<Currency> currencyList = dtos.stream().map(item
                -> objectMapper.convertValue(item, Currency.class)).collect(Collectors.toList());
        for (Currency currency : currencyList) {
            currency.setActive(true);
        }
        currencyRepositorys.saveAll(currencyList);
        return new Respons("succesful", true);
    }

    @Transactional
    public Respons updateCurrency(List<CurrencyDtoForPut> dtos) {
        List<Currency> currencyList = dtos.stream().map(item
                -> objectMapper.convertValue(item, Currency.class)).collect(Collectors.toList());
        currencyRepositorys.saveAll(currencyList);
        return new Respons("succesful", true);
    }

    @Transactional
    public Respons delete(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepositorys.findById(id);
        if (optionalCurrency.isPresent()) {
            Currency currency = optionalCurrency.get();
            currency.setActive(false);
            currencyRepositorys.save(currency);
            return new Respons("Succesful deleted", true);
        }
        return new Respons("Bu idli topilmadi", false);
    }
}
