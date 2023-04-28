package uz.pdp.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.onlinebanking.payload.ServiceCommissionDto;
import uz.pdp.onlinebanking.service.ServiceCommissionService;

@RestController
@RequestMapping("/api/serviceCommission")
public class ServiceCommissionController {
    @Autowired
    ServiceCommissionService serviceCommissionService;
    @PostMapping("/addCommission")
    public ResponseEntity<?> addComission(@RequestBody ServiceCommissionDto dto){
        return serviceCommissionService.addServiceCommission(dto);
    }
    @GetMapping("/getCommissions")
    public ResponseEntity<?> getAllCommission(){
        return serviceCommissionService.getAllCommission();
    }

    @PutMapping("/editCommission/{id}")
    public ResponseEntity<?> editCommission(@PathVariable Integer id, @RequestBody ServiceCommissionDto dto){
        return serviceCommissionService.editCommission(id,dto);
    }
    @GetMapping("/offCommission/{id}")
    public ResponseEntity<?> offCommission(@PathVariable Integer id){
        return serviceCommissionService.offCommission(id);
    }

}
