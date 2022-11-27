package com.company.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class ReplyKeyboardUtil {
    public static ReplyKeyboard getAdminMenu() {

        return getMarkup(getRowList(
                getRow(
                getButton(ReplyKeyboardConstants.CATEGORY_DEMO),
                getButton(ReplyKeyboardConstants.PRODUCT_DEMO)
        ),
                getRow(
                        getButton(ReplyKeyboardConstants.ADVERT_DEMO)
                )
        ));
    }

    private static ReplyKeyboard getMarkup(List<KeyboardRow> rowList) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(rowList);
        markup.setResizeKeyboard(true);
        markup.setSelective(true);
        return markup;
    }

    private static List<KeyboardRow> getRowList(KeyboardRow... rows) {
        return List.of(rows);
    }

    private static KeyboardRow getRow(KeyboardButton... buttons) {
        return new KeyboardRow(List.of(buttons));
    }

    private static KeyboardButton getButton(String demo) {
        return new KeyboardButton(demo);
    }


    public static ReplyKeyboard getCategoryCRUDMenu() {
        return getMarkup(getRowList(
                getRow(
                        getButton(ReplyKeyboardConstants.CATEGORY_ADD),
                        getButton(ReplyKeyboardConstants.CATEGORY_EDIT)
                ),
                getRow(
                        getButton(ReplyKeyboardConstants.CATEGORY_DELETE),
                        getButton(ReplyKeyboardConstants.CATEGORY_LIST)
                ),
                getRow(
                        getButton(ReplyKeyboardConstants.BACK_TO_BASE_MENU)
                )
        ));
    }

    public static ReplyKeyboard getProductCRUDMenu() {
        return getMarkup(getRowList(
                getRow(
                        getButton(ReplyKeyboardConstants.PRODUCT_ADD),
                        getButton(ReplyKeyboardConstants.PRODUCT_EDIT)
                ),
                getRow(
                        getButton(ReplyKeyboardConstants.PRODUCT_DELETE),
                        getButton(ReplyKeyboardConstants.PRODUCT_LIST)
                ),
                getRow(
                        getButton(ReplyKeyboardConstants.BACK_TO_BASE_MENU)
                )
        ));
    }

    public static ReplyKeyboard getUserMenu() {
        return getMarkup(getRowList(
                getRow(
                        getButton(ReplyKeyboardConstants.USER_MENU_DEMO),
                        getButton(ReplyKeyboardConstants.BASKET_DEMO)
                ),
                getRow(
                        getButton(ReplyKeyboardConstants.MY_ORDERS_DEMO)
                ),
                getRow(
                        getButton(ReplyKeyboardConstants.CONTACT_WITH_ADMIN)
                )
        ));
    }

    public static ReplyKeyboard getContactMenu() {
        KeyboardButton button = getButton(ReplyKeyboardConstants.SEND_CONTACT);
        button.setRequestContact(true);

        return getMarkup(getRowList(getRow(button)));
    }
}
