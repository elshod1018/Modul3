package com.company.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBot extends TelegramLongPollingBot {
    @Override
    public String getBotToken() {
        return "5604693111:AAFrAv2DEYgiGm3dw2wX8PwfrYbfDxTLhGQ";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message=update.getMessage();

    }

    @Override
    public String getBotUsername() {
        return "testSystem1_bot";
    }
}
