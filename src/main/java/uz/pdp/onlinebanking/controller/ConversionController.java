package uz.pdp.onlinebanking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.onlinebanking.entity.CurrencyRate;
import uz.pdp.onlinebanking.payload.ConversionPayload.ConversionDto;
import uz.pdp.onlinebanking.payload.ConversionPayload.Respons;
import uz.pdp.onlinebanking.service.ConversionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/coversion")
public class ConversionController {
    private final ConversionService conversionService;

    @GetMapping("course")
    public HttpEntity<?> showCourse(){
        List<CurrencyRate> show = conversionService.show();
        return ResponseEntity.status(200).body(show);
    }

    @PostMapping("buy/{id}")
    public HttpEntity<?> buy(@RequestBody ConversionDto conversionDto,@PathVariable Integer id){
        Respons respons = conversionService.buy(conversionDto,id);
        return ResponseEntity.status(respons.getIsSucces()?200:409).body(respons.getMassage());
    }
    @PostMapping("sell/{id}")
    public HttpEntity<?> sell(@RequestBody ConversionDto conversionDto,@PathVariable Integer id){
        Respons respons = conversionService.sell(conversionDto,id);
        return ResponseEntity.status(respons.getIsSucces()?200:409).body(respons.getMassage());
    }

}
