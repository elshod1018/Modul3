package com.company.util;

import com.company.db.Database;
import com.company.entity.BasketDetail;
import com.company.entity.Category;
import com.company.entity.Product;
import com.company.service.BasketDetailService;
import com.company.service.ProductService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardUtil {

    public static InlineKeyboardMarkup getFileMenuForCategory() {
        InlineKeyboardButton button1 = getButton(
                InlineKeyboardConstants.CATEGORY_PDF_DEMO, InlineKeyboardConstants.CATEGORY_PDF_DATA);
        InlineKeyboardButton button2 = getButton(
                InlineKeyboardConstants.CATEGORY_WORD_DEMO, InlineKeyboardConstants.CATEGORY_WORD_DATA);
        InlineKeyboardButton button3 = getButton(
                InlineKeyboardConstants.CATEGORY_EXCEL_DEMO, InlineKeyboardConstants.CATEGORY_EXCEL_DATA);

//        List<InlineKeyboardButton> row = List.of(button1, button2, button3);

        List<List<InlineKeyboardButton>> rowList = List.of(getRow(button1,button2,button3));

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

    public static ReplyKeyboard getInlineMarkupByCategories(String why) {
        // why = {CATEGORY_DELETE_DATA | CATEGORY_EDIT_DATA | CATEGORY_ADD_PRODUCT_DATA}

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (Category category : Database.CATEGORY_LIST) {
            rowList.add (List.of(getButton(category.getName(), why+"/"+category.getId())));
        }

        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup getCommitOrCancelMenuForProduct() {
        InlineKeyboardButton button1 = getButton(
                InlineKeyboardConstants.PRODUCT_COMMIT_DEMO, InlineKeyboardConstants.PRODUCT_COMMIT_DATA);
        InlineKeyboardButton button2 = getButton(
                InlineKeyboardConstants.PRODUCT_CANCEL_DEMO, InlineKeyboardConstants.PRODUCT_CANCEL_DATA);

        List<InlineKeyboardButton> row = List.of(button1, button2);

        List<List<InlineKeyboardButton>> rowList = List.of(row);

        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup getInlineMarkupByProducts(String why, List<Product> productList) {
        // why = {PRODUCT_ORDER_DATA | ...}

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (Product product : productList) {
            rowList.add (List.of(getButton(product.getName(), why+"/"+product.getId())));
        }

        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup getProductCountMenu(Integer productId, int productQuantity) {
        InlineKeyboardButton buttonMinus = getButton(
                " - ", InlineKeyboardConstants.PRODUCT_QUANTITY_DATA+"/"+productQuantity+"/-1"+"/"+productId);
        InlineKeyboardButton buttonQuantity = getButton(
                String.valueOf(productQuantity), "QWERTYUIOQWERTYUIO");
        InlineKeyboardButton buttonPlus = getButton(
                " + ", InlineKeyboardConstants.PRODUCT_QUANTITY_DATA+"/"+productQuantity+"/1"+"/"+productId);
        InlineKeyboardButton button = getButton(
                InlineKeyboardConstants.PRODUCT_QUANTITY_BASKET_DEMO,
                InlineKeyboardConstants.PRODUCT_QUANTITY_BASKET_DATA + "/"+productId+"/"+productQuantity);

        List<InlineKeyboardButton> row1 = List.of(buttonMinus, buttonQuantity, buttonPlus);
        List<InlineKeyboardButton> row2 = List.of(button);

        List<List<InlineKeyboardButton>> rowList = List.of(row1, row2);

        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup getBasketMenu(String chatId) {

        List<BasketDetail> basketDetailList = BasketDetailService.getBasket(chatId);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (BasketDetail basketDetail : basketDetailList) {
            Product product = ProductService.getProductById(basketDetail.getProductId());

            InlineKeyboardButton button = new InlineKeyboardButton(product.getName() + " ❌");
            button.setCallbackData(InlineKeyboardConstants.PRODUCT_REMOVE_FROM_BASKET_DATA+"/"+product.getId());

            rowList.add(List.of(button));
        }

        InlineKeyboardButton buttonClear = new InlineKeyboardButton(InlineKeyboardConstants.PRODUCT_CLEAN_BASKET_DEMO);
        buttonClear.setCallbackData(InlineKeyboardConstants.PRODUCT_CLEAN_BASKET_DATA);

        InlineKeyboardButton buttonBuy = new InlineKeyboardButton(InlineKeyboardConstants.PRODUCT_BUY_BASKET_DEMO);
        buttonBuy.setCallbackData(InlineKeyboardConstants.PRODUCT_BUY_BASKET_DATA);

        rowList.add(List.of(buttonClear, buttonBuy));

        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup getConfirmOrCancelOrderMenu(Integer orderId) {
        InlineKeyboardButton buttonCommit = new InlineKeyboardButton(InlineKeyboardConstants.ORDER_COMMIT_DEMO);
        buttonCommit.setCallbackData(InlineKeyboardConstants.ORDER_COMMIT_OR_CANCEL_DATA+"/"+orderId+"/true");

        InlineKeyboardButton buttonCancel = new InlineKeyboardButton(InlineKeyboardConstants.ORDER_CANCEL_DEMO);
        buttonCancel.setCallbackData(InlineKeyboardConstants.ORDER_COMMIT_OR_CANCEL_DATA+"/"+orderId+"/false");

        List<InlineKeyboardButton> row1 = List.of(buttonCommit, buttonCancel);

        List<List<InlineKeyboardButton>> rowList = List.of(row1);

        return new InlineKeyboardMarkup(rowList);
    }
}
