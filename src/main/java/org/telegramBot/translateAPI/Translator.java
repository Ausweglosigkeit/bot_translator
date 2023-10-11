package org.telegramBot.translateAPI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.telegramBot.bot.DarkZeit;

public class Translator {
    private static final String TRANSLATION_REGEX = "\"translatedText\":\\s*\"([^\"]+)";

    public String translateRequest(String text, String languageTo, String languageFrom) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.post("https://deep-translate1.p.rapidapi.com/language/translate/v2")
                    .header("content-type", "application/json")
                    .header("X-RapidAPI-Key", DarkZeit.BOT_DATA.getString("api.key"))
                    .header("X-RapidAPI-Host", DarkZeit.BOT_DATA.getString("api.host"))
                    .body(String.format("{\r\"q\": \"%s\",\r\"source\": \"%s\",\r\"target\": \"%s\"\r}", text, languageFrom, languageTo))
                    .asString();

        } catch (UnirestException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = null;

        if (response != null){
            jsonElement = jsonParser.parse(response.getBody());
        }

        String prettyJsonString = gson.toJson(jsonElement);

        Pattern pattern = Pattern.compile(TRANSLATION_REGEX);
        Matcher matcher = pattern.matcher(prettyJsonString);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
