package uz.pdp.onlinebanking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.onlinebanking.payload.ConversionPayload.CurrencyRateDtoForPost;
import uz.pdp.onlinebanking.payload.ConversionPayload.CurrencyRateDtoForPut;
import uz.pdp.onlinebanking.payload.ConversionPayload.Respons;
import uz.pdp.onlinebanking.service.ConversionCurrencyRateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/conversion")
public class ConversionCurrencyRateController {
    private final ConversionCurrencyRateService currencyRateServices;

    @GetMapping("currencyrate")
    public HttpEntity<?> getAllCurrency(){
     return ResponseEntity.status(200).body(currencyRateServices.getAll());
    }

    @GetMapping("currencyrate/{id}")
    public HttpEntity<?> getCurrencyRateById(@PathVariable Integer id){
        Respons respons = currencyRateServices.getById(id);
        return ResponseEntity.status(respons.getIsSucces()?200:409).body(respons);
    }

    @PostMapping("currencyrate")
    public HttpEntity<?> addCurrencyRate(@RequestBody List<CurrencyRateDtoForPost> currencies){
        Respons respons = currencyRateServices.addCurrencyRate(currencies);
        return ResponseEntity.status(respons.getIsSucces()?200:409).body(respons.getMassage());
    }

    @PutMapping("currencyrate")
    public HttpEntity<?> editCurrencyRate(@RequestBody List<CurrencyRateDtoForPut> currencies){
        Respons respons = currencyRateServices.updateCurrencyRate(currencies);
        return ResponseEntity.status(respons.getIsSucces()?200:409).body(respons.getMassage());
    }

    @DeleteMapping("currencyrate/{id}")
    public HttpEntity<?> deleteCurrencyRate(@PathVariable Integer id){
        Respons respons = currencyRateServices.deleteCurrencyRate(id);
        return ResponseEntity.status(respons.getIsSucces()?200:409).body(respons.getMassage());
    }
}
