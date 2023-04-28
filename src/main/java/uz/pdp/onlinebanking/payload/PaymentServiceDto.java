package uz.pdp.onlinebanking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentServiceDto {
    private String name;
    private String logo;
    private Integer paymentCategory;
    private Integer userId;
    private Long accountNumber;
    private boolean active;
}
