package uz.pdp.onlinebanking.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {
    private BigDecimal sum;
    private Integer card;
    private Integer paymentServiceId;
}
