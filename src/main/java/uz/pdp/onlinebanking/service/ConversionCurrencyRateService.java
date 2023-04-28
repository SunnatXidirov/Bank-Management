package uz.pdp.onlinebanking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onlinebanking.entity.Currency;
import uz.pdp.onlinebanking.entity.CurrencyRate;
import uz.pdp.onlinebanking.payload.ConversionPayload.CurrencyRateDtoForPost;
import uz.pdp.onlinebanking.payload.ConversionPayload.CurrencyRateDtoForPut;
import uz.pdp.onlinebanking.payload.ConversionPayload.Respons;
import uz.pdp.onlinebanking.repository.CurrencyRateRepository;
import uz.pdp.onlinebanking.repository.CurrencyRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversionCurrencyRateService {
    private final CurrencyRateRepository currencyRateRepository;
    private final CurrencyRepository currencyRepository;
    private final ObjectMapper objectMapper;

    public List<CurrencyRate> getAll() {
        return currencyRateRepository.findAll();
    }

    public Respons getById(Integer id) {
        Optional<CurrencyRate> rateOptional = currencyRateRepository.findById(id);
        return rateOptional.map(currencyRate ->
                new Respons("Succesful", true, currencyRate)).orElseGet(() ->
                new Respons("Bu idli topilmadi", false));
    }

    @Transactional
    public Respons addCurrencyRate(List<CurrencyRateDtoForPost> currencies) {
        List<CurrencyRate> currencyRates = currencies.stream().
                map(rate -> objectMapper.convertValue(rate, CurrencyRate.class)).collect(Collectors.toList());
        for (CurrencyRate currencyRate : currencyRates) {
            Optional<Currency> optional = currencyRepository.findById(currencyRate.getId());
            if (optional.isPresent()) {
                currencyRate.setCurrency(optional.get());
                currencyRate.setDate(new Date());
                currencyRate.setActive(true);
                currencyRateRepository.saveAll(currencyRates);
                return new Respons("Succesful added", true);
            }
        }
        return new Respons("Currency topilmadi",false);
    }

    @Transactional
    public Respons updateCurrencyRate(List<CurrencyRateDtoForPut> currencies) {
        List<CurrencyRate> currencyRates = currencies.stream().
                map(rate -> objectMapper.convertValue(rate, CurrencyRate.class)).collect(Collectors.toList());
        for (CurrencyRate currencyRate : currencyRates) {
            Optional<Currency> optional = currencyRepository.findById(currencyRate.getId());
            if (optional.isPresent()) {
                currencyRate.setCurrency(optional.get());
                currencyRate.setDate(new Date());
                currencyRateRepository.saveAll(currencyRates);
                return new Respons("Succesful added", true);
            }
        }
        return new Respons("Currency topilmadi",false);
    }

    @Transactional
    public Respons deleteCurrencyRate(Integer id) {
        Optional<CurrencyRate> optionalCurrencyRatel = currencyRateRepository.findById(id);
        if (optionalCurrencyRatel.isPresent()) {
            CurrencyRate currencyRate = optionalCurrencyRatel.get();
            currencyRate.setActive(false);
            currencyRateRepository.save(currencyRate);
            return new Respons("Succesful deleted", true);
        }
        return new Respons("Bu idli topilmadi", false);
    }
}
