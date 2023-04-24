package uz.pdp.onlinebanking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestToken {
    private String tokenType = "Bearer ";
    private String token;
    private String refreshToken;

    public RestToken(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
