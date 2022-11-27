package com.company.container;

import com.company.bot.MyBot;

import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public interface ComponentContainer {
    String BOT_TOKEN="5952424380:AAGiW2u41DVT_JrpbWLO2g12dyuPOsul7eA";
    String BOT_USERNAME="moneyConverterByElshod_bot";
    MyBot MY_BOT=new MyBot();

    Map<String,Object>USER_STATUS=new HashMap<>();
    Map<String,String >FROM_CURRENCY_TO_CURRENCY=new HashMap<>();

}
