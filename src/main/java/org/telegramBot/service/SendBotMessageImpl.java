package org.telegramBot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegramBot.bot.DarkZeit;

import static sun.util.locale.LocaleUtils.isEmpty;

public class SendBotMessageImpl implements SendBotMessage {
    private final DarkZeit darkZeit;

    public SendBotMessageImpl(DarkZeit darkZeit) {
        this.darkZeit = darkZeit;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        if (message.trim().equals("")) {
            return;
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            darkZeit.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
