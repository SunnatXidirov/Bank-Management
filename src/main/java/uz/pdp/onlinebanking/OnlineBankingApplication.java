package uz.pdp.onlinebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.onlinebanking.telegram.TelegramBot;


@SpringBootApplication
public class OnlineBankingApplication {


    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(OnlineBankingApplication.class, args);
    }

}
