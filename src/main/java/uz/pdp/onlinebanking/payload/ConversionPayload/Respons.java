package uz.pdp.onlinebanking.payload.ConversionPayload;

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

    public Respons(String massage, Boolean isSucces) {
        this.massage = massage;
        this.isSucces = isSucces;
    }
}
