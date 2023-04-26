package uz.pdp.onlinebanking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {
    private Integer id;
    private String name;
    private String abbr;
    private boolean active;
}
