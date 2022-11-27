package com.company.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboardUtil {
    public static ReplyKeyboard getMenu(){
        return getMarkup(getRowList(
                getRows(
                        getButton(ReplyKeyboardConstants.CONVERT_DEMO),
                        getButton(ReplyKeyboardConstants.CURRENCIES)
                )
        ));
    }

    public static ReplyKeyboard getMarkup(List<KeyboardRow>rowList){
        ReplyKeyboardMarkup markup=new ReplyKeyboardMarkup(rowList);
        markup.setResizeKeyboard(true);
        markup.setSelective(true);
        return markup;
    }

    public static List<KeyboardRow>getRowList(KeyboardRow...rows){
        return List.of(rows);
    }

    public static KeyboardRow getRows(KeyboardButton...buttons){
        return new KeyboardRow(List.of(buttons));
    }

    public static KeyboardButton getButton(String demo){
        return new KeyboardButton(demo);
    }
}
