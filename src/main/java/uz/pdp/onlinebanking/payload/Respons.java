package uz.pdp.onlinebanking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respons {
    private String massage;
    private Boolean isSucces;
    private Object ResponsObject;
}
