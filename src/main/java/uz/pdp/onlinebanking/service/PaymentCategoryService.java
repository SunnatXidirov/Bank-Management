package uz.pdp.onlinebanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.onlinebanking.entity.PaymentCategory;
import uz.pdp.onlinebanking.payload.PaymentCategoryDto;
import uz.pdp.onlinebanking.repository.PaymentCategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentCategoryService {
    @Autowired
    PaymentCategoryRepository paymentCategoryRepository;

    public ResponseEntity<?> getCategory(Integer id) {
        Optional<PaymentCategory> byId = paymentCategoryRepository.findById(id);
        if (!byId.isPresent()) {
            return ResponseEntity.status(400).body("Not found");
        }
        return ResponseEntity.ok(byId.get());
    }

    public ResponseEntity<?> getAllCategories() {
        List<PaymentCategory> categories = paymentCategoryRepository.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.status(400).body("Not found");
        }
        return ResponseEntity.ok(categories);
    }

    public ResponseEntity<?> addCategory(PaymentCategoryDto dto) {
        if (dto == null) {
            return ResponseEntity.status(400).body("Not found");
        }
        String name = dto.getName();
        boolean active = dto.isActive();
        PaymentCategory category = new PaymentCategory();
        category.setName(name);
        category.setActive(active);
        paymentCategoryRepository.save(category);
        return ResponseEntity.ok(category);
    }

    public ResponseEntity<?> editCategoty(Integer id, PaymentCategoryDto dto) {
        Optional<PaymentCategory> category = paymentCategoryRepository.findById(id);
        if (category.isPresent()) {
            PaymentCategory paymentCategory = category.get();
            paymentCategory.setName(dto.getName());
            paymentCategory.setActive(dto.isActive());
            paymentCategoryRepository.save(paymentCategory);
            return ResponseEntity.ok(paymentCategory);
        }
        return ResponseEntity.status(400).body("Not found");
    }

    public ResponseEntity<?> deleteCategory(Integer id) {
        paymentCategoryRepository.deleteById(id);
        return ResponseEntity.status(200).body("Deleted");
    }
}
