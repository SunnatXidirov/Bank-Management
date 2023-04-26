package uz.pdp.onlinebanking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.onlinebanking.entity.Currency;
import uz.pdp.onlinebanking.payload.CurrencyDto;
import uz.pdp.onlinebanking.payload.Respons;
import uz.pdp.onlinebanking.service.ConversionCurrencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/conversion")
public class ConversionCurrencyController {

    private final ConversionCurrencyService currencyService;

    @GetMapping("currency")
    public HttpEntity<?> getAllCurrency(){
        List<Currency> allCurrency = currencyService.getAllCurrency();
        return ResponseEntity.status(200).body(allCurrency);
    }

    @GetMapping("currency/{id}")
    public HttpEntity<?> getCurrencyById(@PathVariable Integer id){
        Respons respons = currencyService.getCurrencyById(id);
        return ResponseEntity.status(respons.getIsSucces()?200:409).body(respons);
    }

    @PostMapping("currency")
    public HttpEntity<?> addCurrency(@RequestBody List<CurrencyDto> currencies){
        Respons respons = currencyService.addOrUpdateCurrency(currencies);
        return ResponseEntity.status(respons.getIsSucces()?201:409).body(respons);
    }

    @PutMapping("currency")
    public HttpEntity<?> editCurrency(@RequestBody List<CurrencyDto> currencies){
        Respons respons = currencyService.addOrUpdateCurrency(currencies);
        return ResponseEntity.status(respons.getIsSucces()?201:409).body(respons);
    }

}
