package com.company.container;

import com.company.Main;
import com.company.bot.MyBot;
import com.company.enums.AdminStatus;

import java.util.HashMap;
import java.util.Map;

public interface ComponentContainer {
    String BOT_TOKEN = "5849920736:AAHkKnPHa4lKfsoHvOY28V4e9-tPEMxZPIE";
    String BOT_USERNAME = "http://t.me/PapuGayTuit_bot";
    String ADMIN_CHAT_ID = "175290742111";
    MyBot MY_BOT = new MyBot();

    // chat id, admin status
    Map<String, AdminStatus> adminStatusMap = new HashMap<>();

    // chat id, object
    Map<String, Object> adminObjectMap = new HashMap<>();

}
