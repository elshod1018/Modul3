package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.db.Database;
import com.company.entity.Category;
import com.company.enums.AdminStatus;
import com.company.files.WorkWithFiles;
import com.company.service.CategoryService;
import com.company.util.InlineKeyboardConstants;
import com.company.util.InlineKeyboardUtil;
import com.company.util.ReplyKeyboardConstants;
import com.company.util.ReplyKeyboardUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class AdminController {
    public static void handleMessage(Message message) {

        if (message.hasText()) {
            handleText(message);
        } else if (message.hasContact()) {
            handleContact(message);
        } else if (message.hasLocation()) {
            handleLocation(message);
        } else if (message.hasPhoto()) {
            handlePhoto(message);
        } else if (message.hasDocument()) {
            handleDocument(message);
        }
    }

    private static void handleDocument(Message message) {
        String chatId = String.valueOf(message.getChatId());
        // ...
    }

    private static void handlePhoto(Message message) {
        String chatId = String.valueOf(message.getChatId());
        List<PhotoSize> photoSizeList = message.getPhoto();


    }

    private static void handleLocation(Message message) {
        String chatId = String.valueOf(message.getChatId());
        Location location = message.getLocation();


    }

    private static void handleContact(Message message) {
        String chatId = String.valueOf(message.getChatId());
        Contact contact = message.getContact();

    }

    private static void handleText(Message message) {
        String chatId = String.valueOf(message.getChatId());
        String text = message.getText();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (text.equals("/start")) {
            sendMessage.setText("Welcome, admin!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getAdminMenu());
            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        } else if (text.equals(ReplyKeyboardConstants.CATEGORY_DEMO)) {
            sendMessage.setText("Choose operation:");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getCategoryCRUDMenu());
            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        } else if (text.equals(ReplyKeyboardConstants.PRODUCT_DEMO)) {

        } else if (text.equals(ReplyKeyboardConstants.CATEGORY_ADD)) {
            sendMessage.setText("Enter category name:");
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
            ComponentContainer.MY_BOT.sendMsg(sendMessage);

            ComponentContainer.adminStatusMap.put(chatId, AdminStatus.ENTER_CATEGORY_NAME_FOR_ADD);
        } else if (text.equals(ReplyKeyboardConstants.CATEGORY_EDIT)) {
            if (Database.CATEGORY_LIST.isEmpty()) {
                sendMessage.setText("No categories");
                ComponentContainer.MY_BOT.sendMsg(sendMessage);
            } else {
                sendMessage.setText("Choose category for edit");
                sendMessage.setReplyMarkup(InlineKeyboardUtil.getInlineMarkupByCategories(InlineKeyboardConstants.CATEGORY_EDIT_DATA));
                ComponentContainer.MY_BOT.sendMsg(sendMessage);
            }
        } else if (text.equals(ReplyKeyboardConstants.CATEGORY_DELETE)) {
            if (Database.CATEGORY_LIST.isEmpty()) {
                sendMessage.setText("No categories");
                ComponentContainer.MY_BOT.sendMsg(sendMessage);
            } else {
                sendMessage.setText("Choose category for delete");
                sendMessage.setReplyMarkup(InlineKeyboardUtil.getInlineMarkupByCategories(InlineKeyboardConstants.CATEGORY_DELETE_DATA));
                ComponentContainer.MY_BOT.sendMsg(sendMessage);
            }
        } else if (text.equals(ReplyKeyboardConstants.CATEGORY_LIST)) {
            if (Database.CATEGORY_LIST.isEmpty()) {
                sendMessage.setText("No categories");
                ComponentContainer.MY_BOT.sendMsg(sendMessage);

            } else {
//                String reduce = Database.CATEGORY_LIST.stream()
//                        .map(Category::toString)
//                        .reduce("", (s, s2) -> s + "\n" + s2);
//
//                sendMessage.setText(reduce);
//                ComponentContainer.MY_BOT.sendMsg(sendMessage);

                sendMessage.setText("Select file type");
                sendMessage.setReplyMarkup(InlineKeyboardUtil.getFileMenuForCategory());
                ComponentContainer.MY_BOT.sendMsg(sendMessage);
            }
        } else if (text.equals(ReplyKeyboardConstants.BACK_FROM_CATEGORY_MENU)) {

        } else {
            if (ComponentContainer.adminStatusMap.containsKey(chatId)) {
                AdminStatus adminStatus = ComponentContainer.adminStatusMap.get(chatId);

                if (adminStatus.equals(AdminStatus.ENTER_CATEGORY_NAME_FOR_ADD)) {
                    String response = CategoryService.addCategory(text);
                    sendMessage.setText(response);
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.getCategoryCRUDMenu());
                    ComponentContainer.MY_BOT.sendMsg(sendMessage);

                    ComponentContainer.adminStatusMap.remove(chatId);
                }else if (adminStatus.equals(AdminStatus.ENTER_CATEGORY_NAME_FOR_EDIT)) {
                    String response = CategoryService.editCategory(text,
                            (Integer)ComponentContainer.adminObjectMap.get(chatId));
                    sendMessage.setText(response);
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.getCategoryCRUDMenu());
                    ComponentContainer.MY_BOT.sendMsg(sendMessage);

                    ComponentContainer.adminStatusMap.remove(chatId);
                    ComponentContainer.adminObjectMap.remove(chatId);
                }
            }
        }

    }

    public static void handleCallback(Message message, String data) {
        String chatId = String.valueOf(message.getChatId());

        System.out.println("chatId = " + chatId);
        System.out.println("data = " + data);

        DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
        ComponentContainer.MY_BOT.sendMsg(deleteMessage);

        if (List.of(InlineKeyboardConstants.CATEGORY_PDF_DATA,
                InlineKeyboardConstants.CATEGORY_WORD_DATA,
                InlineKeyboardConstants.CATEGORY_EXCEL_DATA).contains(data)) {

            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);

            File file = null;

            if (data.equals(InlineKeyboardConstants.CATEGORY_PDF_DATA)) {
                file = WorkWithFiles.getCategoriesWithPDF();
            } else if (data.equals(InlineKeyboardConstants.CATEGORY_WORD_DATA)) {
                // todo
            } else if (data.equals(InlineKeyboardConstants.CATEGORY_EXCEL_DATA)) {
                // todo
            }

            if (file != null) {
                sendDocument.setDocument(new InputFile(file));
                ComponentContainer.MY_BOT.sendMsg(sendDocument);
            } else {
                SendMessage sendMessage = new SendMessage(chatId, "Some exception");
                ComponentContainer.MY_BOT.sendMsg(sendMessage);
            }

        } else if (data.startsWith(InlineKeyboardConstants.CATEGORY_DELETE_DATA)) {
            Integer categoryId = Integer.parseInt(data.split("/")[1]);

            boolean removeIf = Database.CATEGORY_LIST
                    .removeIf(category -> category.getId().equals(categoryId));

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(removeIf ? "Category deleted" : "Category not found");
            ComponentContainer.MY_BOT.sendMsg(sendMessage);

        } else if (data.startsWith(InlineKeyboardConstants.CATEGORY_EDIT_DATA)) {
            Integer categoryId = Integer.parseInt(data.split("/")[1]);

            Optional<Category> categoryOptional = Database.CATEGORY_LIST.stream()
                    .filter(category -> category.getId().equals(categoryId))
                    .findFirst();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            if(categoryOptional.isPresent()){
                sendMessage.setText("Enter new name");
                sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));

                ComponentContainer.adminObjectMap.put(chatId, categoryId);
                ComponentContainer.adminStatusMap.put(chatId, AdminStatus.ENTER_CATEGORY_NAME_FOR_EDIT);

            }else{
                sendMessage.setText("Category not found");
            }

            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        }

    }
}
