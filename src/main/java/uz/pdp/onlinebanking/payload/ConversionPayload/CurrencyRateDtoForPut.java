package uz.pdp.onlinebanking.payload.ConversionPayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateDtoForPut {
    private Integer id;
    private Integer currencyId;
    private String actionType;
    private BigDecimal rate;
    private Date date;
    private boolean active;
}
