package uz.pdp.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.onlinebanking.payload.PaymentCategoryDto;
import uz.pdp.onlinebanking.service.PaymentCategoryService;

@RestController
@RequestMapping("/api/paymentCategory")
public class PaymentCategoryController {
    @Autowired
    PaymentCategoryService paymentCategoryService;

    @GetMapping("/getCategory/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Integer id) {
        return paymentCategoryService.getCategory(id);
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<?> getAllCategories() {
        return paymentCategoryService.getAllCategories();
    }

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody PaymentCategoryDto dto) {
        return paymentCategoryService.addCategory(dto);
    }

    @PutMapping("/editCategory/{id}")
    public ResponseEntity<?> editCategory(@PathVariable Integer id, @RequestBody PaymentCategoryDto dto) {
        return paymentCategoryService.editCategoty(id, dto);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        return paymentCategoryService.deleteCategory(id);
    }

}
