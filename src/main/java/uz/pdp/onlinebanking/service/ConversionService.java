package uz.pdp.onlinebanking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onlinebanking.entity.Card;
import uz.pdp.onlinebanking.entity.Conversion;
import uz.pdp.onlinebanking.entity.CurrencyRate;
import uz.pdp.onlinebanking.entity.Users;
import uz.pdp.onlinebanking.payload.ConversionPayload.ConversionDto;
import uz.pdp.onlinebanking.payload.ConversionPayload.Respons;
import uz.pdp.onlinebanking.repository.CardRepository;
import uz.pdp.onlinebanking.repository.ConversionRepository;
import uz.pdp.onlinebanking.repository.CurrencyRateRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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

    public List<CurrencyRate> show() {
        return currencyRateRepositorys.existsByActiveTrue();
    }

    @Transactional
    public Respons buy(ConversionDto conversionDto, Integer currencyId) {

        if (conversionDto.getActionType().equals("BUY")) {

            Optional<Card> optionalFromCard = cardRepository.findById(conversionDto.getFromCardId());
            Optional<Card> optionalTargetCard = cardRepository.findById(conversionDto.getTargetCardId());

            //KARTANILARNI TEKSHIRDIM
            if (optionalTargetCard.isPresent() && optionalFromCard.isPresent()) {

                Card targetCard = optionalTargetCard.get();
                Card fromCard = optionalFromCard.get();

                //KARTALARNING YAROQLILIK MUDATINI TEKSHIRDIM
                if (targetCard.getExpireDate().before(new Date()) && fromCard.getExpireDate().before(new Date())) {

                    //VISA KARTAGA TEKSHIRISH
                    if (targetCard.getCvv() != 0) {

                        Users targetUsersId = targetCard.getUsersId();
                        Users fromUsersId = fromCard.getUsersId();

                        //USERLARNI TEKSHIRISH
                        if (targetUsersId.isActive() && fromUsersId.isActive()) {

                            CurrencyRate currencyRate = currencyRateRepositorys.findByCurrency_Id(currencyId);
                            String actionType = currencyRate.getActionType().toString();

                            if (actionType.equals("BUY")) {
                                long rate = currencyRate.getRate().longValue();
                                long fromCardBalance = fromCard.getBalance().longValue();
                                long targetCardBalance = targetCard.getBalance().longValue();
                                long amount = conversionDto.getAmount().longValue();
                                long checkBalance = fromCardBalance - amount * rate;

                                //KARTANING MABLAG'INI TEKSHIRDIM
                                if (checkBalance > 0) {

                                    fromCard.setBalance(BigDecimal.valueOf(checkBalance));
                                    targetCard.setBalance(BigDecimal.valueOf(targetCardBalance + rate));
                                    cardRepository.save(fromCard);
                                    cardRepository.save(targetCard);
                                    Conversion conversion = objectMapper.convertValue(conversionDto, Conversion.class);
                                    conversion.setDate(new Date());
                                    conversionRepositorys.save(conversion);
                                    return new Respons("Tranzaksiya muvofaqiyatli amalga oshdi", true);

                                } else {
                                    return new Respons("Mablag' yetarli emas", false);
                                }
                            } else {
                                return new Respons("action type xato", false);
                            }

                        } else {
                            return new Respons("User blocked", false);
                        }

                    } else {
                        return new Respons("Cvv da xatolik", false);
                    }

                } else {
                    return new Respons("Muddati o'tgan karta", false);
                }

            } else {
                return new Respons("Bunday karta topilmadi", false);
            }

        }
        return new Respons("Xato buyruq", false);
    }

    @Transactional
    public Respons sell(ConversionDto conversionDto, Integer currencyId) {

        if (conversionDto.getActionType().equals("SELL")) {

            Optional<Card> optionalFromCard = cardRepository.findById(conversionDto.getFromCardId());
            Optional<Card> optionalTargetCard = cardRepository.findById(conversionDto.getTargetCardId());

            //KARTANILARNI TEKSHIRDIM
            if (optionalTargetCard.isPresent() && optionalFromCard.isPresent()) {

                Card targetCard = optionalTargetCard.get();
                Card fromCard = optionalFromCard.get();

                //KARTALARNING YAROQLILIK MUDATINI TEKSHIRDIM
                if (targetCard.getExpireDate().before(new Date()) && fromCard.getExpireDate().before(new Date())) {

                    //VISA KARTAGA TEKSHIRISH
                    if (fromCard.getCvv() != 0) {

                        Users targetUsersId = targetCard.getUsersId();
                        Users fromUsersId = fromCard.getUsersId();

                        //USERLARNI TEKSHIRISH
                        if (targetUsersId.isActive() && fromUsersId.isActive()) {

                            CurrencyRate currencyRate = currencyRateRepositorys.findByCurrency_Id(currencyId);
                            String actionType = currencyRate.getActionType().toString();

                            if (actionType.equals("SELL")) {
                                long rate = currencyRate.getRate().longValue();
                                long fromCardBalance = fromCard.getBalance().longValue();
                                long targetCardBalance = targetCard.getBalance().longValue();
                                long amount = conversionDto.getAmount().longValue();
                                long checkBalance = fromCardBalance - amount;

                                //KARTANING MABLAG'INI TEKSHIRDIM
                                if (checkBalance > 0) {

                                    fromCard.setBalance(BigDecimal.valueOf(checkBalance));
                                    targetCard.setBalance(BigDecimal.valueOf(targetCardBalance + rate * amount));
                                    cardRepository.save(fromCard);
                                    cardRepository.save(targetCard);
                                    Conversion conversion = objectMapper.convertValue(conversionDto, Conversion.class);
                                    conversion.setDate(new Date());
                                    conversionRepositorys.save(conversion);
                                    return new Respons("Tranzaksiya muvofaqiyatli amalga oshdi", true);

                                } else {
                                    return new Respons("Mablag' yetarli emas", false);
                                }
                            }else {
                                return new Respons("action type xato", false);
                            }

                        } else {
                            return new Respons("User blocked", false);
                        }

                    } else {
                        return new Respons("Cvv da xatolik", false);
                    }

                } else {
                    return new Respons("Muddati o'tgan karta", false);
                }

            } else {
                return new Respons("Bunday karta topilmadi", false);
            }

        }
        return new Respons("Xato buyruq", false);
    }
}

