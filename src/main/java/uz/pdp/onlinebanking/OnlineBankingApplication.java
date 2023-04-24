package uz.pdp.onlinebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.pdp.onlinebanking.security.JwtProvider;

@SpringBootApplication
public class OnlineBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineBankingApplication.class, args);
        JwtProvider jwtProvider = new JwtProvider();
        String s = jwtProvider.generateJwtToken(1);
        System.out.println(s);


    }

}
