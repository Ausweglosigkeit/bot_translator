package org.telegramBot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegramBot.bot.DarkZeit;
import java.io.File;

public class SendBotMessageServiceImpl implements SendBotMessageService {
    private final DarkZeit darkZeit;

    public SendBotMessageServiceImpl(DarkZeit darkZeit) {
        this.darkZeit = darkZeit;
    }

    @Override
    public void sendMessage(String chatId, String message) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        execution(sendMessage);
    }

    @Override
    public void sendMessageWithInlineButton(SendMessage sendMessage, String chatId, String message) {

        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        execution(sendMessage);
    }

    @Override
    public void sendEditMessageText(EditMessageText messageText, long chatId, int messageId, String text) {

        messageText.setChatId(chatId);
        messageText.setText(text);
        messageText.setMessageId(messageId);

        execution(messageText);
    }

    @Override
    public void sendPhoto(String chatId, String imagePath, String imageCaption) {
        File photo = new File(imagePath);
        InputFile inputFile = new InputFile(photo);
        SendPhoto sendPhoto = new SendPhoto();

        sendPhoto.setPhoto(inputFile);
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption(imageCaption);

        execution(sendPhoto);
    }

    @Override
    public void sendVideo(String chatId, String videoPath, String videoCaption) {
        File video = new File(videoPath);
        InputFile inputFile = new InputFile(video);
        SendVideo sendVideo = new SendVideo();

        sendVideo.setVideo(inputFile);
        sendVideo.setChatId(chatId);
        sendVideo.setCaption(videoCaption);

        execution(sendVideo);
    }

    private void execution(SendVideo sendVideo) {
        try {
            darkZeit.execute(sendVideo);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void execution(SendMessage sendMessage) {
        try {
            darkZeit.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void execution(SendPhoto sendPhoto) {
        try {
            darkZeit.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void execution(EditMessageText editMessageText) {
        try {
            darkZeit.execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
