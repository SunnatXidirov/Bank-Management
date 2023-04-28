package uz.pdp.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.onlinebanking.payload.PaymentDto;
import uz.pdp.onlinebanking.service.PaymentServices;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentServices paymentServices;
    @PostMapping("/pay")
    public ResponseEntity<?> pay(@RequestBody PaymentDto dto) {
        return paymentServices.pay(dto);
    }
}
