package uz.pdp.onlinebanking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onlinebanking.entity.*;
import uz.pdp.onlinebanking.entity.enums.ActionType;
import uz.pdp.onlinebanking.entity.enums.CurrencyAbbr;
import uz.pdp.onlinebanking.payload.ConversionPayload.ConversionDto;
import uz.pdp.onlinebanking.payload.ConversionPayload.Respons;
import uz.pdp.onlinebanking.repository.CardRepository;
import uz.pdp.onlinebanking.repository.ConversionRepository;
import uz.pdp.onlinebanking.repository.CurrencyRateRepository;
import uz.pdp.onlinebanking.repository.CurrencyRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConversionService {
    private final ConversionRepository conversionRepositorys;
    private final ObjectMapper objectMapper;
    private final CurrencyRateRepository currencyRateRepositorys;
    private final CardRepository cardRepository;
    private final CurrencyRepository currencyRepository;

    public List<CurrencyRate> show() {
      return currencyRateRepositorys.findAllByActiveTrue();
    }

    @Transactional
    public Respons convension(ConversionDto conversionDto) {

        Card fromCard = cardRepository.findById(conversionDto.getFromCardId()).orElseThrow(
                () -> new IllegalArgumentException("Card not found"));

        Card toCard = cardRepository.findById(conversionDto.getTargetCardId()).orElseThrow(
                () -> new IllegalArgumentException("Card not found"));

        Optional<Currency> optionalCurrency = currencyRepository.findByIdAndActiveTrue(conversionDto.getCurrencyId());
        if (optionalCurrency.isPresent()) {

            Currency currency = optionalCurrency.get();
            BigDecimal totalSum = new BigDecimal(0);

            if (conversionDto.getActionType().equals(ActionType.BUY)) {

                CurrencyRate buyRate = currencyRateRepositorys.findByCurrencyAndActionTypeAndActiveTrue(currency, ActionType.BUY);
                totalSum.add(conversionDto.getAmount().multiply(buyRate.getRate()));

                if (fromCard.getCvv() == null && toCard.getCvv() != null && toCard.getCurrency().getId().equals(currency.getId()) &&
                        fromCard.getBalance().compareTo(conversionDto.getAmount()) >= 0) {

                    // 1 CHI KARTADAN SUMMA YECHISH
                    fromCard.setBalance(fromCard.getBalance().subtract(totalSum));
                    // 2 CHI KARTAGA SUMMA QO'SHISH
                    toCard.setBalance(toCard.getBalance().add(conversionDto.getAmount()));
                    cardRepository.save(fromCard);
                    cardRepository.save(toCard);
                    Conversion conversion = objectMapper.convertValue(conversionDto, Conversion.class);
                    conversion.setDate(new Date());
                    conversionRepositorys.save(conversion);
                    return new Respons("Tranzaksiya muvofaqiyatli amalga oshdi", true);

                } else {
                    return new Respons("Xato karta kiritildi", false);
                }
            } else {

                if (fromCard.getCvv() != null && toCard.getCvv() == null && fromCard.getCurrency().getId().equals(currency.getId()) &&
                        fromCard.getBalance().compareTo(conversionDto.getAmount()) >= 0) {

                    CurrencyRate buyRate = currencyRateRepositorys.
                            findByCurrencyAndActionTypeAndActiveTrue(currency, ActionType.BUY);
                    totalSum.add(conversionDto.getAmount().multiply(buyRate.getRate()));

                    // 1 CHI KARTADAN SUMMA YECHISH
                    fromCard.setBalance(fromCard.getBalance().subtract(conversionDto.getAmount()));
                    // 2 CHI KARTAGA SUMMA QO'SHISH
                    toCard.setBalance(toCard.getBalance().add(totalSum));
                    cardRepository.save(fromCard);
                    cardRepository.save(toCard);
                    Conversion conversion = objectMapper.convertValue(conversionDto, Conversion.class);
                    conversion.setDate(new Date());
                    conversionRepositorys.save(conversion);
                    return new Respons("Tranzaksiya muvofaqiyatli amalga oshdi", true);


                } else {
                    return new Respons("Xato karta kiritildi", false);
                }
            }
        }
        return new Respons("Valyutada xatolik", false);
    }
}

