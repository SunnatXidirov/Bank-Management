package uz.pdp.onlinebanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.onlinebanking.entity.Card;
import uz.pdp.onlinebanking.entity.ServiceCommission;
import uz.pdp.onlinebanking.payload.PaymentDto;
import uz.pdp.onlinebanking.repository.CardRepository;
import uz.pdp.onlinebanking.repository.PaymentRepository;
import uz.pdp.onlinebanking.repository.ServiceCommissionRepository;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PaymentServices {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    ServiceCommissionRepository serviceCommissionRepository;

    public ResponseEntity<?> pay(PaymentDto dto) {
        Integer card = dto.getCard();
        Optional<Card> byId = cardRepository.findById(card);
        Integer paymentServiceId = dto.getPaymentServiceId();
        ServiceCommission byIdAndActiveTrue = serviceCommissionRepository.findByIdAndActiveTrue(dto.getPaymentServiceId());
        if (byId.isPresent() ) {
            Card card1 = byId.get();
            BigDecimal balance = card1.getBalance();
            long cardBalance = balance.longValue();

            BigDecimal sum = dto.getSum();
            long dtoBalance = sum.longValue();

            float checkBalance = cardBalance - dtoBalance - ((dtoBalance / 100) * byIdAndActiveTrue.getCommissionPercent());

            if (checkBalance >= 0) {
                card1.setBalance(BigDecimal.valueOf(checkBalance));
                cardRepository.save(card1);
                return ResponseEntity.ok(card1);
            }
            return ResponseEntity.status(400).body("Kartada pul yetmaydi");
        }
        return ResponseEntity.status(400).body("Bunday karta mavjud emas");
    }
}
