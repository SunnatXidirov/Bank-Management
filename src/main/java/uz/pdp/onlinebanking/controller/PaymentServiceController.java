package uz.pdp.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.onlinebanking.payload.PaymentServiceDto;
import uz.pdp.onlinebanking.service.PaymentServiceService;

@RestController
@RequestMapping("/api/paymentService")
public class PaymentServiceController {
    @Autowired
    PaymentServiceService paymentServiceService;

    @GetMapping("/getAllPaymentService")
    public ResponseEntity<?> getAllService() {
        return paymentServiceService.getAllPaymentService();
    }

    @GetMapping("/getPaymentService/{id}")
    public ResponseEntity<?> getPaymentServiceWithId(@PathVariable Integer id) {
        return paymentServiceService.getPaymentServiceWithId(id);
    }

    @PutMapping("/editPaymentService/{id}")
    public ResponseEntity<?> editPaymentService(@PathVariable Integer id, @RequestBody PaymentServiceDto dto) {
        return paymentServiceService.editPaymentService(id, dto);
    }

    @PostMapping("/addPaymentService")
    public ResponseEntity<?> addPaymentService(@RequestBody PaymentServiceDto dto) {
        return paymentServiceService.addPaymentService(dto);
    }
    @PutMapping("/deletePaymentService/{id}")
    public ResponseEntity<?> deletePaymentService(@PathVariable Integer id){
        return paymentServiceService.deletePaymentService(id);
    }
}
