package uz.pdp.onlinebanking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.onlinebanking.entity.Currency;
import uz.pdp.onlinebanking.payload.ConversionPayload.CurrencyDtoForPost;
import uz.pdp.onlinebanking.payload.ConversionPayload.CurrencyDtoForPut;
import uz.pdp.onlinebanking.payload.ConversionPayload.Respons;
import uz.pdp.onlinebanking.service.ConversionCurrencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conversion/")
public class ConversionCurrencyController {

    private final ConversionCurrencyService currencyServices;

    @GetMapping("currency")
    public HttpEntity<?> getAllCurrency(){
        List<Currency> allCurrency = currencyServices.getAllCurrency();
        return ResponseEntity.status(200).body(allCurrency);
    }

    @GetMapping("currency/{id}")
    public HttpEntity<?> getCurrencyById(@PathVariable Integer id){
        Respons respons = currencyServices.getCurrencyById(id);
        return ResponseEntity.status(respons.getIsSucces()?200:409).body(respons);
    }

    @PostMapping("currency")
    public HttpEntity<?> addCurrency(@RequestBody List<CurrencyDtoForPost> currencies){
        Respons respons = currencyServices.addCurrency(currencies);
        return ResponseEntity.status(respons.getIsSucces()?201:409).body(respons);
    }

    @PutMapping("currency")
    public HttpEntity<?> editCurrency(@RequestBody List<CurrencyDtoForPut> currencies){
        Respons respons = currencyServices.updateCurrency(currencies);
        return ResponseEntity.status(respons.getIsSucces()?201:409).body(respons);
    }

    @DeleteMapping("currency/{id}")
    public HttpEntity<?> deleteCurrency(@PathVariable Integer id){
        Respons respons = currencyServices.delete(id);
        return ResponseEntity.status(respons.getIsSucces()?200:409).body(respons.getMassage());
    }

}
