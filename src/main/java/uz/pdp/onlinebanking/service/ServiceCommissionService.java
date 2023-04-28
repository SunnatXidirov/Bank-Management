package uz.pdp.onlinebanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.onlinebanking.entity.PaymentService;
import uz.pdp.onlinebanking.entity.ServiceCommission;
import uz.pdp.onlinebanking.payload.ServiceCommissionDto;
import uz.pdp.onlinebanking.repository.PaymentServiceRepository;
import uz.pdp.onlinebanking.repository.ServiceCommissionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCommissionService {
    @Autowired
    ServiceCommissionRepository serviceCommissionRepository;
    @Autowired
    PaymentServiceRepository paymentServiceRepository;

    public ResponseEntity<?> getAllCommission() {
        List<ServiceCommission> allByActiveTrue = serviceCommissionRepository.findAllByActiveTrue();

        return ResponseEntity.ok(allByActiveTrue);
    }

    public ResponseEntity<?> editCommission(Integer id, ServiceCommissionDto dto) {
        Optional<ServiceCommission> commission = serviceCommissionRepository.findById(id);

        if (commission.isPresent()) {

            Integer paymentServiceId = dto.getPaymentService();
            Optional<PaymentService> paymentService = paymentServiceRepository.findById(paymentServiceId);

            if (paymentService.isPresent()) {
                ServiceCommission serviceCommission = commission.get();
                PaymentService paymentService1 = paymentService.get();

                serviceCommission.setPaymentService(paymentService1);
                serviceCommission.setCommissionPercent(dto.getCommissionPercent());
                serviceCommission.setActive(dto.isActive());

                serviceCommissionRepository.save(serviceCommission);
                return ResponseEntity.ok(serviceCommission);
            }
        }
        return ResponseEntity.status(400).body("not found");
    }

    public ResponseEntity<?> offCommission(Integer id) {

        Optional<ServiceCommission> byId = serviceCommissionRepository.findById(id);
        if(byId.isPresent()){
            ServiceCommission serviceCommission = byId.get();
            serviceCommission.setActive(false);
        return ResponseEntity.ok(serviceCommission);
        }
        return ResponseEntity.status(400).body("Not found");
    }

    public ResponseEntity<?> addServiceCommission(ServiceCommissionDto dto) {
        Integer paymentServiceId = dto.getPaymentService();
        Optional<PaymentService> paymentService = paymentServiceRepository.findById(paymentServiceId);

        if (paymentService.isPresent()){
        float commissionPercent = dto.getCommissionPercent();
            PaymentService paymentService1 = paymentService.get();

           ServiceCommission serviceCommission = new ServiceCommission();
           serviceCommission.setActive(dto.isActive());
           serviceCommission.setPaymentService(paymentService1);
           serviceCommission.setCommissionPercent(commissionPercent);
           serviceCommissionRepository.save(serviceCommission);

           return ResponseEntity.ok(serviceCommission);
        }
        return ResponseEntity.status(400).body("Not found service Comission");
    }
}
