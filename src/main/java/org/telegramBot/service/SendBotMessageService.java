package org.telegramBot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public interface SendBotMessageService {
    void sendMessage(String chatId, String message);
    void sendMessageWithInlineButton(SendMessage sendMessage, String chatId, String message);
    void sendEditMessageText(EditMessageText messageText, long chatId, int messageId, String text);
    void sendPhoto(String chatId, String imagePath, String imageCaption);
    void sendVideo(String chatId, String videoCaption, String videoName);
}
