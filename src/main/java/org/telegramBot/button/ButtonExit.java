package org.telegramBot.button;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.telegramBot.command.CommandName.ENDQUIZ;

public class ButtonExit {
    private static final InlineKeyboardMarkup inlineKeyboardExitQuiz = new InlineKeyboardMarkup();
    private static final List<List<InlineKeyboardButton>> exitLine = new ArrayList<>();
    private static final List<InlineKeyboardButton> buttonExit = new ArrayList<>();
    private static final String textButton = "Выйти";
    private static final String buttonId = "exit" + " " + ENDQUIZ.getCommandName();


    public ButtonExit() {
        createButton();
    }

    public void attachKeyboard(SendMessage sendMessage) {
        sendMessage.setReplyMarkup(inlineKeyboardExitQuiz);
    }

    public void detachKeyboard(SendMessage sendMessage) {
        sendMessage.setReplyMarkup(null);
    }

    private void createButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(textButton);
        button.setCallbackData(buttonId);

        buttonExit.add(button);
        exitLine.add(buttonExit);
        inlineKeyboardExitQuiz.setKeyboard(exitLine);
    }
}
