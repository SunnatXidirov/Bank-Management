package uz.pdp.onlinebanking.telegram;

import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot  {
    private static final String BOT_USERNAME = "Online_bank_uz_bot";
    private static final String BOT_TOKEN = "6092517489:AAG6G-tqhfhV_Af_MZHRngjjaRQPVTyuZE8";
    private static final String BOT_WEBHOOK_URL = "https://b03e-178-218-201-17.eu.ngrok.io/api/bot";

/*
    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }


    @Override
    public String getBotPath() {
        return "/api/bot";
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("You said: " + messageText);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
*/
}
