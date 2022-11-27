package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.db.Database;
import com.company.utils.InlineKeyboardConstants;
import com.company.utils.InlineKeyboardUtil;
import com.company.utils.ReplyKeyboardConstants;
import com.company.utils.ReplyKeyboardUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.File;

public class MainController {
    public static void handleMessage(Message message) {
        if (message.hasText()) {
            handleText(message);
        }
    }

    private static void handleText(Message message) {
        String text = message.getText();
        String chatId = String.valueOf(message.getChatId());
        if (text.equals("/start")) {
            SendMessage sendMessage = new SendMessage(chatId, "Choose operation: ");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getMenu());
            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        } else if (text.equals(ReplyKeyboardConstants.CURRENCIES)) {
            SendMessage sendMessage = new SendMessage(chatId, "Choose file type: ");
            sendMessage.setReplyMarkup(InlineKeyboardUtil.getFileType());

            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        }else if (text.equals(ReplyKeyboardConstants.CONVERT_DEMO)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("From currency:");
            sendMessage.setChatId(chatId);
            sendMessage.setReplyMarkup(InlineKeyboardUtil.getCurrenciesMarkup());

            ComponentContainer.USER_STATUS.put(chatId, "from");

            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        } else if(ComponentContainer.USER_STATUS.get(chatId).equals("to")){

            try {
                Double value= Double.valueOf(text);
                String strings[]=ComponentContainer.FROM_CURRENCY_TO_CURRENCY.get(chatId).split("/");
                Double from= Double.valueOf(strings[1]);
                Double to= Double.valueOf(strings[3]);
                double res = value * from / to;
                SendMessage sendMessage=new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(text+"  "+strings[0]+" = "+res+"  "+ strings[2]);

                ComponentContainer.MY_BOT.sendMsg(sendMessage);
                ComponentContainer.FROM_CURRENCY_TO_CURRENCY.remove(chatId);
                ComponentContainer.USER_STATUS.remove(chatId);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }

        }


    }

    public static void handleCallback(Message message, String data) {

        String chatId = String.valueOf(message.getChatId());

        if (ComponentContainer.USER_STATUS.containsKey(chatId)&&
                ComponentContainer.USER_STATUS.get(chatId).equals("from")) {

            ComponentContainer.FROM_CURRENCY_TO_CURRENCY.put(chatId,data+"/");
            ComponentContainer.USER_STATUS.remove(chatId);
            ComponentContainer.USER_STATUS.put(chatId, "to");

            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(chatId);
            editMessageText.setMessageId(message.getMessageId());
            editMessageText.setText("To currency: ");
            editMessageText.setReplyMarkup((InlineKeyboardMarkup) InlineKeyboardUtil.getCurrenciesMarkup());

            ComponentContainer.MY_BOT.sendMsg(editMessageText);
        } else if (ComponentContainer.USER_STATUS.containsKey(chatId)&&
                ComponentContainer.USER_STATUS.get(chatId).equals("to")) {


            ComponentContainer.FROM_CURRENCY_TO_CURRENCY.put(chatId,
                    ComponentContainer.FROM_CURRENCY_TO_CURRENCY.get(chatId)+data+"/");

            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(chatId);
            editMessageText.setMessageId(message.getMessageId());
            editMessageText.setText("Continue ");
//            editMessageText.setReplyMarkup((InlineKeyboardMarkup) InlineKeyboardUtil.getCurrenciesMarkup());

            ComponentContainer.MY_BOT.sendMsg(editMessageText);

            SendMessage sendMessage=new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("Enter value: ");

            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        } else if (data.equals(InlineKeyboardConstants.CURRENCY_PDF_DATA)) {

            File pdfFile=Database.currenciesPDF();
            InputFile file=new InputFile(pdfFile);
            SendDocument document=new SendDocument();
            document.setChatId(chatId);
            document.setDocument(file);
            System.out.println(document);
            ComponentContainer.MY_BOT.sendMsg(document);

        }else if (data.equals(InlineKeyboardConstants.CURRENCY_WORD_DATA)) {
            File wordFile=Database.currenciesWord();
            InputFile file=new InputFile(wordFile);
            SendDocument document=new SendDocument();
            document.setChatId(chatId);
            document.setDocument(file);

            ComponentContainer.MY_BOT.sendMsg(document);
        }
    }
}
