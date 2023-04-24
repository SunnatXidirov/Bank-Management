package uz.pdp.onlinebanking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingUp {
    private String login;
    private String fullName;
    private String phoneNumber;
    private String password;
}
