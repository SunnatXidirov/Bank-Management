package uz.pdp.onlinebanking.payload.ConversionPayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionDto {
    private Integer fromCardId;
    private Integer targetCardId;
    private BigDecimal amount;
    private String actionType;
}
