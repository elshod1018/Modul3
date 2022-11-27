package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.db.Database;
import com.company.entity.*;
import com.company.enums.AdminStatus;
import com.company.enums.UserStatus;
import com.company.files.WorkWithJsonFiles;
import com.company.service.BasketDetailService;
import com.company.service.CustomerService;
import com.company.service.ProductService;
import com.company.util.InlineKeyboardConstants;
import com.company.util.InlineKeyboardUtil;
import com.company.util.ReplyKeyboardConstants;
import com.company.util.ReplyKeyboardUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

public class MainController {

    public static void handleMessage(Message message) {
        String chatId = String.valueOf(message.getChatId());

        if (chatId.equals(ComponentContainer.ADMIN_CHAT_ID)) {
            AdminController.handleMessage(message);
            return;
        }

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

        if (ComponentContainer.userStatusMap.containsKey(chatId)) {
            UserStatus userStatus = ComponentContainer.userStatusMap.get(chatId);

            if (userStatus.equals(UserStatus.SEND_CONTACT)) {
                Customer customer = CustomerService.getCustomerByChatId(chatId);
                customer.setFirstName(contact.getFirstName());
                customer.setLastName(contact.getLastName());
                customer.setPhoneNumber(contact.getPhoneNumber());

                Integer orderId = sendNotificationToAdminByNewOrder(customer);

                Database.BASKET_DETAIL_LIST.removeIf(basketDetail -> basketDetail.getChatId().equals(chatId));

                SendMessage sendMessage = new SendMessage(chatId, "Our managers will contact with you\n" +
                        "Your current order id: " + orderId);
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserMenu());
                ComponentContainer.MY_BOT.sendMsg(sendMessage);

                ComponentContainer.userStatusMap.remove(chatId);
            }
        }
    }

    private static Integer sendNotificationToAdminByNewOrder(Customer customer) {

        StringBuilder sb = new StringBuilder("New order\n\n");

        sb.append("Customer: ").append(customer.getFirstName()).append(" ")
                .append(customer.getLastName()).append("\n");
        sb.append("Phone number: ").append(customer.getPhoneNumber()).append("\n");
        sb.append("Chat id: ").append(customer.getChatId()).append("\n\n");

        Order order = new Order(Database.ORDER_LIST.size() + 1, customer.getChatId());

        List<BasketDetail> basketDetailList = BasketDetailService.getBasket(customer.getChatId());

        double total = 0;

        for (BasketDetail basketDetail : basketDetailList) {

            OrderDetail orderDetail = new OrderDetail(
                    order.getId(), basketDetail.getProductId(), basketDetail.getQuantity());

            Database.ORDER_DETAIL_LIST.add(orderDetail);
            WorkWithJsonFiles.AddOrderDetailToJson();

            Product product = ProductService.getProductById(basketDetail.getProductId());

            total += basketDetail.getQuantity() * product.getPrice();

            sb.append(product.getName()).append(" x ").append(basketDetail.getQuantity())
                    .append(" = ").append(basketDetail.getQuantity() * product.getPrice()).append("\n\n");
        }

        sb.append("Total price:     ").append(total);

        order.setTotalPrice(total);

        Database.ORDER_LIST.add(order);
        WorkWithJsonFiles.AddOrderToJson();

        SendMessage sendMessage = new SendMessage(ComponentContainer.ADMIN_CHAT_ID, sb.toString());
        sendMessage.setReplyMarkup(InlineKeyboardUtil.getConfirmOrCancelOrderMenu(order.getId()));
        ComponentContainer.MY_BOT.sendMsg(sendMessage);

        return order.getId();
    }

    private static void handleText(Message message) {
        String chatId = String.valueOf(message.getChatId());
        String text = message.getText();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        User user = message.getFrom();

        if (text.equals("/start")) {

            CustomerService.addCustomerByChatId(chatId);

            sendMessage.setText("Welcome, " + user.getFirstName());
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserMenu());
            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        } else if (text.equals(ReplyKeyboardConstants.USER_MENU_DEMO)) {
            sendMessage.setText("Choose category");
            sendMessage.setReplyMarkup(InlineKeyboardUtil.getInlineMarkupByCategories(
                    InlineKeyboardConstants.CATEGORY_ORDER_DATA
            ));
            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        } else if (text.equals(ReplyKeyboardConstants.BASKET_DEMO)) {
            showBasket(chatId);
        } else if (text.equals(ReplyKeyboardConstants.MY_ORDERS_DEMO)) {
            // todo
        } else if (text.equals(ReplyKeyboardConstants.CONTACT_WITH_ADMIN)) {
            // todo
        }
    }

    private static void showBasket(String chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        List<BasketDetail> basketDetailList = BasketDetailService.getBasket(chatId);

        if (basketDetailList.isEmpty()) {
            sendMessage.setText("Basket is empty");
        } else {
            StringBuilder sb = new StringBuilder("Products: \n\n");
            double totalPrice = 0;
            for (BasketDetail basketDetail : basketDetailList) {
                Product product = ProductService.getProductById(basketDetail.getProductId());

                sb.append(product.getName());
                sb.append(" x ");
                sb.append(basketDetail.getQuantity());
                sb.append(" = ");
                sb.append(basketDetail.getQuantity() * product.getPrice());
                sb.append("\n");

                totalPrice += basketDetail.getQuantity() * product.getPrice();
            }

            sb.append("\nTotal price :   ").append(totalPrice);
            sendMessage.setText(sb.toString());
            sendMessage.setReplyMarkup(InlineKeyboardUtil.getBasketMenu(chatId));
        }

        ComponentContainer.MY_BOT.sendMsg(sendMessage);
    }

    public static void handleCallback(Message message, String data) {

        String chatId = String.valueOf(message.getChatId());

        if (chatId.equals(ComponentContainer.ADMIN_CHAT_ID)) {
            AdminController.handleCallback(message, data);
            return;
        }
        // write code here
        System.out.println("chatId = " + chatId);
        System.out.println("data = " + data);

        if (data.startsWith(InlineKeyboardConstants.CATEGORY_ORDER_DATA)) {
            Integer categoryId = Integer.parseInt(data.split("/")[1]);

            List<Product> productList = ProductService.getProductListByCategoryId(categoryId);

            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(chatId);
            editMessageText.setMessageId(message.getMessageId());

            if (productList.isEmpty()) {
                editMessageText.setText("No products by this category");
            } else {
                editMessageText.setText("Choose product");
                editMessageText.setReplyMarkup(InlineKeyboardUtil.getInlineMarkupByProducts(
                        InlineKeyboardConstants.PRODUCT_ORDER_DATA, productList
                ));
            }

            ComponentContainer.MY_BOT.sendMsg(editMessageText);
        } else if (data.startsWith(InlineKeyboardConstants.PRODUCT_ORDER_DATA)) {
            Integer productId = Integer.parseInt(data.split("/")[1]);

            Product product = ProductService.getProductById(productId);

            if (product == null) {
                EditMessageText editMessageText = new EditMessageText();
                editMessageText.setChatId(chatId);
                editMessageText.setMessageId(message.getMessageId());
                editMessageText.setText("Product not found");

                ComponentContainer.MY_BOT.sendMsg(editMessageText);
            } else {

                DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
                ComponentContainer.MY_BOT.sendMsg(deleteMessage);

                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(chatId);
                sendPhoto.setPhoto(new InputFile(product.getImageUrl()));
                sendPhoto.setCaption(product.detailInfo());
                sendPhoto.setReplyMarkup(InlineKeyboardUtil.getProductCountMenu(productId, 1));
                ComponentContainer.MY_BOT.sendMsg(sendPhoto);
            }
        } else if (data.startsWith(InlineKeyboardConstants.PRODUCT_QUANTITY_DATA)) {
            // data = InlineKeyboardConstants.PRODUCT_QUANTITY_DATA+"/"+productQuantity+"/-1"
            // data = InlineKeyboardConstants.PRODUCT_QUANTITY_DATA+"/"+productQuantity+"/1"

            String[] parts = data.split("/");
            int productQuantity = Integer.parseInt(parts[1]);
            int sign = Integer.parseInt(parts[2]);
            int productId = Integer.parseInt(parts[3]);

            InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardUtil.getProductCountMenu(
                    productId, Math.max(productQuantity + sign, 1));

            EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
            editMessageReplyMarkup.setChatId(chatId);
            editMessageReplyMarkup.setMessageId(message.getMessageId());
            editMessageReplyMarkup.setReplyMarkup(inlineKeyboardMarkup);

            ComponentContainer.MY_BOT.sendMsg(editMessageReplyMarkup);

        } else if (data.startsWith(InlineKeyboardConstants.PRODUCT_QUANTITY_BASKET_DATA)) {
            // data = InlineKeyboardConstants.PRODUCT_QUANTITY_BASKET_DATA + "/"+productId+"/"+productQuantity

            String[] parts = data.split("/");
            int productId = Integer.parseInt(parts[1]);
            int productQuantity = Integer.parseInt(parts[2]);

            List<BasketDetail> basketDetailList = BasketDetailService.getBasket(chatId);

            BasketDetail basketDetail = basketDetailList.stream()
                    .filter(bd -> bd.getProductId().equals(productId))
                    .findFirst().orElse(null);

            if (basketDetail == null) {
                basketDetail = new BasketDetail(chatId, productId, productQuantity);
                Database.BASKET_DETAIL_LIST.add(basketDetail);
                WorkWithJsonFiles.AddBasketDetailToJson();
            } else {
                basketDetail.setQuantity(basketDetail.getQuantity() + productQuantity);
            }

            DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
            ComponentContainer.MY_BOT.sendMsg(deleteMessage);

            Product product = ProductService.getProductById(productId);

            SendMessage sendMessage = new SendMessage(chatId, product.getName() + " added to basket");
            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        } else if (data.startsWith(InlineKeyboardConstants.PRODUCT_REMOVE_FROM_BASKET_DATA)) {
            Integer productId = Integer.valueOf(data.split("/")[1]);

            boolean b = Database.BASKET_DETAIL_LIST.removeIf(basketDetail -> basketDetail.getChatId().equals(chatId) && basketDetail.getProductId().equals(productId));
            if (b) {
                WorkWithJsonFiles.AddBasketDetailToJson();
            }
            DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
            ComponentContainer.MY_BOT.sendMsg(deleteMessage);

            showBasket(chatId);
        } else if (data.equals(InlineKeyboardConstants.PRODUCT_CLEAN_BASKET_DATA)) {

            boolean b = Database.BASKET_DETAIL_LIST.removeIf(basketDetail -> basketDetail.getChatId().equals(chatId));
            if (b) {
                WorkWithJsonFiles.AddBasketDetailToJson();
            }

            DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
            ComponentContainer.MY_BOT.sendMsg(deleteMessage);

            showBasket(chatId);
        } else if (data.equals(InlineKeyboardConstants.PRODUCT_BUY_BASKET_DATA)) {

            DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
            ComponentContainer.MY_BOT.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(chatId, "Send your contact");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getContactMenu());
            ComponentContainer.MY_BOT.sendMsg(sendMessage);

            ComponentContainer.userStatusMap.put(chatId, UserStatus.SEND_CONTACT);
        }
    }
}
