package com.company.container;

import com.company.Main;
import com.company.bot.MyBot;
import com.company.enums.AdminStatus;
import com.company.enums.UserStatus;

import java.util.HashMap;
import java.util.Map;

public interface ComponentContainer {
    String BOT_TOKEN = "5871130182:AAHH_a08BBkek1jeOLUTO-SoKKaBWyA3zZA";
    String BOT_USERNAME = "http://t.me/soldProductBot";
    String ADMIN_CHAT_ID = "5304980315";
    MyBot MY_BOT = new MyBot();

    // chat id, admin status
    Map<String, AdminStatus> adminStatusMap = new HashMap<>();

    Map<String, UserStatus> userStatusMap = new HashMap<>();

    // chat id, object
    Map<String, Object> adminObjectMap = new HashMap<>();
}
