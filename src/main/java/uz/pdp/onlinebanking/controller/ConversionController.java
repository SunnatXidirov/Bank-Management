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
@RequestMapping("/api/conversion/")
public class ConversionController {
    private final ConversionService conversionService;

    @GetMapping("show")
    public HttpEntity<?> showCourse(){
        List<CurrencyRate> show = conversionService.show();
        return ResponseEntity.status(200).body(show);
    }

    @PostMapping
    public HttpEntity<?> convension(@RequestBody ConversionDto conversionDto){
        Respons respons = conversionService.convension(conversionDto);
        return ResponseEntity.status(respons.getIsSucces()?200:409).body(respons.getMassage());
    }

}
