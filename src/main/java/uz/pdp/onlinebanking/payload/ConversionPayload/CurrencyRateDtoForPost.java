package uz.pdp.onlinebanking.payload.ConversionPayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateDtoForPost {
    private Integer currencyId;
    private String actionType;
    private BigDecimal rate;

}
