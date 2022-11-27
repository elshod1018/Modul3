package com.company.utils;

import com.company.db.Database;
import com.company.entity.Currency;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardUtil {
    public static ReplyKeyboard getCurrenciesMarkup() {

        List<Currency> currencies = Database.getCurrencies();
//        System.out.println(currencies);
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        List<InlineKeyboardButton> rows=new ArrayList<>();
        InlineKeyboardButton buttonUZS=new InlineKeyboardButton();
        buttonUZS.setText("UZS");
        buttonUZS.setCallbackData("UZS/1");
        rows.add(buttonUZS);

        for (int i = 0; i < currencies.size(); i++) {
            InlineKeyboardButton button=new InlineKeyboardButton();
            button.setText(currencies.get(i).getCcy());
            button.setCallbackData(currencies.get(i).getCcy()+"/"+currencies.get(i).getRate());
            rows.add(button);
            if ((i)%5==0){
                rowList.add(rows);
                rows=new ArrayList<>();
            }
        }
        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup getFileType() {
        InlineKeyboardButton button1 = getButton(
                InlineKeyboardConstants.CURRENCY_PDF_DEMO, InlineKeyboardConstants.CURRENCY_PDF_DATA);
        InlineKeyboardButton button2 = getButton(
                InlineKeyboardConstants.CURRENCY_WORD_DEMO, InlineKeyboardConstants.CURRENCY_WORD_DATA);
//        InlineKeyboardButton button3 = getButton(
//                InlineKeyboardConstants.CURRENCY_EXCEL_DEMO, InlineKeyboardConstants.CURRENCY_EXCEL_DATA);

        List<List<InlineKeyboardButton>> rowList = List.of(getRow(button1,button2));

        return new InlineKeyboardMarkup(rowList);
    }
    public static List<InlineKeyboardButton> getRow(InlineKeyboardButton...buttons){
        return List.of(buttons);
    }
    private static InlineKeyboardButton getButton(String demo, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(demo);
        button.setCallbackData(callbackData);
        return button;
    }
}

