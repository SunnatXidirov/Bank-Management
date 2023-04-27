package uz.pdp.onlinebanking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bot/")
@RequiredArgsConstructor
public class BotController {
    @PostMapping
    public void onUpdateReceived(@RequestBody Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasContact()) {
                Contact contact = update.getMessage().getContact();
                User from = update.getMessage().getFrom();
                Long userId = contact.getUserId();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId());
                if (from.getId().equals(userId)){
                    System.out.println("Ozini contacti ni share qildi" + contact.getFirstName());
                    sendMessage.setText("Rahmat");
                } else {
                    System.out.println("Bu boshqa contact" + contact.getPhoneNumber());
                    sendMessage.setText("Ozini contactini jonat");
                }

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.postForEntity("https://api.telegram.org/bot6092517489:AAG6G-tqhfhV_Af_MZHRngjjaRQPVTyuZE8/sendMessage", sendMessage, Object.class);

            } else {
                String text = update.getMessage().getText();
                if (text.equals("/start")) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(update.getMessage().getChatId());
                    ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
                    List<KeyboardRow> keyboardRowList = new ArrayList<>();
                    KeyboardRow row1 = new KeyboardRow();
                    KeyboardButton button1 = new KeyboardButton();
                    button1.setText("Share Contact");
                    button1.setRequestContact(true);
                    row1.add(button1);
                    keyboardRowList.add(row1);
                    markup.setKeyboard(keyboardRowList);
                    sendMessage.setReplyMarkup(markup);
                    sendMessage.setText("Salom!");
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.postForEntity("https://api.telegram.org/bot6092517489:AAG6G-tqhfhV_Af_MZHRngjjaRQPVTyuZE8/sendMessage", sendMessage, Object.class);
                }
            }
        }
    }
}
