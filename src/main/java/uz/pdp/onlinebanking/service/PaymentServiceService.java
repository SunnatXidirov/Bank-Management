package uz.pdp.onlinebanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.onlinebanking.entity.PaymentCategory;
import uz.pdp.onlinebanking.entity.PaymentService;
import uz.pdp.onlinebanking.entity.Users;
import uz.pdp.onlinebanking.payload.PaymentServiceDto;
import uz.pdp.onlinebanking.repository.PaymentCategoryRepository;
import uz.pdp.onlinebanking.repository.PaymentServiceRepository;
import uz.pdp.onlinebanking.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceService {
    @Autowired
    PaymentServiceRepository paymentServiceRepository;
    @Autowired
    PaymentCategoryRepository paymentCategoryRepository;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> getAllPaymentService() {
        List<PaymentService> paymentServices = paymentServiceRepository.findAll();
        return ResponseEntity.ok(paymentServices);
    }

    public ResponseEntity<?> getPaymentServiceWithId(Integer id) {
        Optional<PaymentService> paymentService = paymentServiceRepository.findById(id);

        if (paymentService.isPresent()) {

            PaymentService paymentService1 = paymentService.get();
            return ResponseEntity.ok(paymentService1);
        }
        return ResponseEntity.status(400).body("Not found");
    }

    public ResponseEntity<?> editPaymentService(Integer id, PaymentServiceDto dto) {
        Optional<PaymentService> paymentService = paymentServiceRepository.findById(id);

        Integer paymentCategory = dto.getPaymentCategory();
        Optional<PaymentCategory> category = paymentCategoryRepository.findById(paymentCategory);

        Integer userId = dto.getUserId();
        Optional<Users> users = userRepository.findById(id);

        if (paymentService.isPresent() && category.isPresent() && users.isPresent()) {

            PaymentService paymentService1 = paymentService.get();
            PaymentCategory paymentCategory1 = category.get();
            Users users1 = users.get();

            paymentService1.setPaymentCategory(paymentCategory1);
            paymentService1.setName(dto.getName());
            paymentService1.setLogo(dto.getLogo());
            paymentService1.setActive(dto.isActive());
            paymentService1.setAccountNumber(dto.getAccountNumber());
            paymentService1.setUserId(users1);

            paymentServiceRepository.save(paymentService1);
            return ResponseEntity.ok(paymentService1);
        }
        return ResponseEntity.status(400).body("Payment service Not found");
    }

    public ResponseEntity<?> addPaymentService(PaymentServiceDto dto) {
        Integer userId = dto.getUserId();
        Integer paymentCategory = dto.getPaymentCategory();

        Optional<PaymentCategory> category = paymentCategoryRepository.findById(paymentCategory);
        Optional<Users> users = userRepository.findById(userId);

        if (category.isPresent() && users.isPresent()) {
            PaymentCategory paymentCategory1 = category.get();
            Users users1 = users.get();

            PaymentService paymentService = new PaymentService();

            paymentService.setPaymentCategory(paymentCategory1);
            paymentService.setName(dto.getName());
            paymentService.setLogo(dto.getLogo());
            paymentService.setUserId(users1);
            paymentService.setActive(dto.isActive());
            paymentService.setName(dto.getName());

            paymentServiceRepository.save(paymentService);
            return ResponseEntity.ok(paymentService);
        }
        return ResponseEntity.status(400).body("Not added");
    }

    public ResponseEntity<?> deletePaymentService(Integer id) {
        Optional<PaymentService> paymentService = paymentServiceRepository.findById(id);

        if(paymentService.isPresent()){

            PaymentService paymentService1 = paymentService.get();
            paymentServiceRepository.delete(paymentService1);

            return ResponseEntity.ok(paymentService1);
        }
        return ResponseEntity.status(400).body("Payment service not found");
    }
}
