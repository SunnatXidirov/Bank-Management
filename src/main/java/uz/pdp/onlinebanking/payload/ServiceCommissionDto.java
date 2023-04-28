package uz.pdp.onlinebanking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceCommissionDto {
    private float commissionPercent;
    private Integer paymentService;
    private boolean active;
}
