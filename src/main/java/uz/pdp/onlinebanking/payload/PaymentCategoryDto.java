package uz.pdp.onlinebanking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCategoryDto {
    private String name;
    private boolean isActive;
}
