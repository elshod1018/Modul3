package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.db.Database;
import com.company.entity.Category;
import com.company.entity.Customer;
import com.company.entity.Order;
import com.company.entity.Product;
import com.company.enums.AdminStatus;
import com.company.enums.OrderStatus;
import com.company.files.WorkWithFiles;
import com.company.service.CategoryService;
import com.company.service.OrderService;
import com.company.service.ProductService;
import com.company.util.InlineKeyboardConstants;
import com.company.util.InlineKeyboardUtil;
import com.company.util.ReplyKeyboardConstants;
import com.company.util.ReplyKeyboardUtil;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AdminController {
    public static void handleMessage(Message message) {

        String chatId = String.valueOf(message.getChatId());

        if (ComponentContainer.adminStatusMap.containsKey(chatId)) {
            AdminStatus adminStatus = ComponentContainer.adminStatusMap.get(chatId);

            if (adminStatus.equals(AdminStatus.SHARE_ADVERT)) {

                for (Customer customer : Database.CUSTOMER_LIST) {
                    ForwardMessage forwardMessage = new ForwardMessage(customer.getChatId(), chatId, message.getMessageId());
                    forwardMessage.setProtectContent(true);
                    ComponentContainer.MY_BOT.sendMsg(forwardMessage);
                }

                SendMessage sendMessage = new SendMessage(chatId, "Advert shared");
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.getAdminMenu());
                ComponentContainer.MY_BOT.sendMsg(sendMessage);

                ComponentContainer.adminStatusMap.remove(chatId);
                return;
            }
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

        String fileId = photoSizeList.get(photoSizeList.size() - 1).getFileId();
        System.out.println("fileId = " + fileId);

        if (ComponentContainer.adminStatusMap.containsKey(chatId)) {
            AdminStatus adminStatus = ComponentContainer.adminStatusMap.get(chatId);

            if (adminStatus.equals(AdminStatus.SEND_PRODUCT_IMAGE_FOR_ADD)) {

                Product product = (Product) ComponentContainer.adminObjectMap.get(chatId);
                product.setImageUrl(fileId);

                String productDetail = product.detailInfo();

                productDetail += "\n\n" + "Do you want to save product?";

                SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(fileId));
                sendPhoto.setCaption(productDetail);
                sendPhoto.setReplyMarkup(InlineKeyboardUtil.getCommitOrCancelMenuForProduct());

                sendPhoto.setParseMode(ParseMode.HTML);

                ComponentContainer.MY_BOT.sendMsg(sendPhoto);
            }
        }


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
            sendMessage.setText("Choose operation:");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getProductCRUDMenu());
            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        } else if (text.equals(ReplyKeyboardConstants.ADVERT_DEMO)) {
            sendMessage.setText("Send advert:");
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
            ComponentContainer.MY_BOT.sendMsg(sendMessage);

            ComponentContainer.adminStatusMap.put(chatId, AdminStatus.SHARE_ADVERT);
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
        } else if (text.equals(ReplyKeyboardConstants.BACK_TO_BASE_MENU)) {
            sendMessage.setText("...");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getAdminMenu());
            ComponentContainer.MY_BOT.sendMsg(sendMessage);
        } else if (text.equals(ReplyKeyboardConstants.PRODUCT_ADD)) {
            sendMessage.setText("...");
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
            ComponentContainer.MY_BOT.sendMsg(sendMessage);

            sendMessage.setText("Choose category:");
            sendMessage.setReplyMarkup(InlineKeyboardUtil.getInlineMarkupByCategories(
                    InlineKeyboardConstants.CATEGORY_ADD_PRODUCT_DATA));
            ComponentContainer.MY_BOT.sendMsg(sendMessage);

            ComponentContainer.adminObjectMap.put(chatId, new Product());
        } else if (text.equals(ReplyKeyboardConstants.PRODUCT_EDIT)) {

        } else if (text.equals(ReplyKeyboardConstants.PRODUCT_DELETE)) {

        } else if (text.equals(ReplyKeyboardConstants.PRODUCT_LIST)) {
            if (Database.PRODUCT_LIST.isEmpty()) {
                sendMessage.setText("No products");
                ComponentContainer.MY_BOT.sendMsg(sendMessage);
            } else {
//                String reduce = Database.PRODUCT_LIST.stream()
//                        .map(Product::toString)
//                        .reduce("", (s, s2) -> s + "\n" + s2);
//
//                sendMessage.setText(reduce);
//                ComponentContainer.MY_BOT.sendMsg(sendMessage);

                for (Product product : Database.PRODUCT_LIST) {
                    SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(product.getImageUrl()));
                    sendPhoto.setCaption(product.detailInfo());
                    ComponentContainer.MY_BOT.sendMsg(sendPhoto);
                }
            }

        } else {
            if (ComponentContainer.adminStatusMap.containsKey(chatId)) {
                AdminStatus adminStatus = ComponentContainer.adminStatusMap.get(chatId);

                if (adminStatus.equals(AdminStatus.ENTER_CATEGORY_NAME_FOR_ADD)) {
                    String response = CategoryService.addCategory(text);
                    sendMessage.setText(response);
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.getCategoryCRUDMenu());
                    ComponentContainer.MY_BOT.sendMsg(sendMessage);

                    ComponentContainer.adminStatusMap.remove(chatId);
                } else if (adminStatus.equals(AdminStatus.ENTER_CATEGORY_NAME_FOR_EDIT)) {
                    String response = CategoryService.editCategory(text,
                            (Integer) ComponentContainer.adminObjectMap.get(chatId));
                    sendMessage.setText(response);
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.getCategoryCRUDMenu());
                    ComponentContainer.MY_BOT.sendMsg(sendMessage);

                    ComponentContainer.adminStatusMap.remove(chatId);
                    ComponentContainer.adminObjectMap.remove(chatId);
                } else if (adminStatus.equals(AdminStatus.ENTER_PRODUCT_NAME_FOR_ADD)) {
                    Product product = (Product) ComponentContainer.adminObjectMap.get(chatId);
                    product.setName(text);

                    sendMessage.setText("Enter product price:");
                    ComponentContainer.MY_BOT.sendMsg(sendMessage);

                    ComponentContainer.adminStatusMap.put(chatId, AdminStatus.ENTER_PRODUCT_PRICE_FOR_ADD);
                } else if (adminStatus.equals(AdminStatus.ENTER_PRODUCT_PRICE_FOR_ADD)) {

                    double price = -5;

                    try {
                        price = Double.parseDouble(text);
                    } catch (Exception e) {

                    }

                    if (price < 0) {
                        sendMessage.setText("Wrong price. Try again");
                    } else {
                        Product product = (Product) ComponentContainer.adminObjectMap.get(chatId);
                        product.setPrice(price);

                        sendMessage.setText("Product description");
                        ComponentContainer.adminStatusMap.put(chatId, AdminStatus.ENTER_PRODUCT_DESCRITION_FOR_ADD);
                    }

                    ComponentContainer.MY_BOT.sendMsg(sendMessage);
                } else if (adminStatus.equals(AdminStatus.ENTER_PRODUCT_DESCRITION_FOR_ADD)) {
                    Product product = (Product) ComponentContainer.adminObjectMap.get(chatId);
                    product.setDescription(text);

                    System.out.println("product = " + product);

                    sendMessage.setText("Send product image:");
                    ComponentContainer.MY_BOT.sendMsg(sendMessage);

                    ComponentContainer.adminStatusMap.put(chatId, AdminStatus.SEND_PRODUCT_IMAGE_FOR_ADD);
                }
            }
        }

    }

    public static void handleCallback(Message message, String data) {
        String chatId = String.valueOf(message.getChatId());

        System.out.println("chatId = " + chatId);
        System.out.println("data = " + data);

        if (data.startsWith(InlineKeyboardConstants.ORDER_COMMIT_OR_CANCEL_DATA)) {
            String[] split = data.split("/");
            Integer orderId = Integer.valueOf(split[1]);

            Order order = OrderService.getOrderById(orderId);
            SendMessage sendMessage = new SendMessage(order.getChatId(), "");

            String messageText = "";

            if (split[2].equals("true")) {
                order.setStatus(OrderStatus.COMMITTED);
                sendMessage.setText("Your order committed (order id: " + orderId + ")");
                messageText = "Order committed (order id: " + orderId + ")";
                // todo send to customer receipt PDF
                File cheque = getCheque(chatId);
            } else {
                order.setStatus(OrderStatus.CANCELED);
                sendMessage.setText("Your order canceled (order id: " + orderId + ")");
                messageText = "Order canceled (order id: " + orderId + ")";
            }

            ComponentContainer.MY_BOT.sendMsg(sendMessage);

            EditMessageText editMessageText = new EditMessageText(messageText);
            editMessageText.setChatId(chatId);
            editMessageText.setMessageId(message.getMessageId());
            ComponentContainer.MY_BOT.sendMsg(editMessageText);
        } else {

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

                if (categoryOptional.isPresent()) {
                    sendMessage.setText("Enter new name");
                    sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));

                    ComponentContainer.adminObjectMap.put(chatId, categoryId);
                    ComponentContainer.adminStatusMap.put(chatId, AdminStatus.ENTER_CATEGORY_NAME_FOR_EDIT);

                } else {
                    sendMessage.setText("Category not found");
                }

                ComponentContainer.MY_BOT.sendMsg(sendMessage);
            } else if (data.startsWith(InlineKeyboardConstants.CATEGORY_ADD_PRODUCT_DATA)) {
                Integer categoryId = Integer.parseInt(data.split("/")[1]);

                Product product = (Product) ComponentContainer.adminObjectMap.get(chatId);
                product.setCategoryId(categoryId);

                System.out.println("product = " + product);

                SendMessage sendMessage = new SendMessage(chatId, "Enter product name:");
                ComponentContainer.MY_BOT.sendMsg(sendMessage);

                ComponentContainer.adminStatusMap.put(chatId, AdminStatus.ENTER_PRODUCT_NAME_FOR_ADD);
            } else if (data.equals(InlineKeyboardConstants.PRODUCT_COMMIT_DATA)) {

                Product product = (Product) ComponentContainer.adminObjectMap.get(chatId);
                String response = ProductService.addProduct(product);

                SendMessage sendMessage = new SendMessage(chatId, response);
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.getProductCRUDMenu());
                ComponentContainer.MY_BOT.sendMsg(sendMessage);

                ComponentContainer.adminStatusMap.remove(chatId);
                ComponentContainer.adminObjectMap.remove(chatId);

            } else if (data.equals(InlineKeyboardConstants.PRODUCT_CANCEL_DATA)) {
                SendMessage sendMessage = new SendMessage(chatId, "Operation canceled");
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.getProductCRUDMenu());
                ComponentContainer.MY_BOT.sendMsg(sendMessage);

                ComponentContainer.adminStatusMap.remove(chatId);
                ComponentContainer.adminObjectMap.remove(chatId);
            }
        }

    }

    private static File getCheque(String chatId) {
        File file = new File("src/main/resources/pdfs/", chatId + ".pdf");
        file.getParentFile().mkdirs();

        ;
        try (PdfWriter pdfWriter = new PdfWriter(file);
             PdfDocument pdfDocument = new PdfDocument(pdfWriter);
             Document document = new Document(pdfDocument)) {

            Paragraph paragraph=new Paragraph("Cheque");
            paragraph.setTextAlignment(TextAlignment.CENTER);
            paragraph.setBold();
            document.add(paragraph);

            float []columns={};
            Table table=new Table(columns);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return file;
    }
}
