package uz.pdp.onlinebanking.payload.ConversionPayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDtoForPut {
    private Integer id;
    private String name;
    private String abbr;
    private boolean active;
}
