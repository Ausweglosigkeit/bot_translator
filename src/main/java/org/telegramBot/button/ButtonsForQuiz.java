package org.telegramBot.button;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.telegramBot.command.CommandName.QUIZ;
import static org.telegramBot.translateAPI.Language.*;

public class ButtonsForQuiz {
    private static final InlineKeyboardMarkup inlineKeyboardWithLanguage = new InlineKeyboardMarkup();
    private static final List<List<InlineKeyboardButton>> rowsLine = new ArrayList<>();
    private static final List<InlineKeyboardButton> firstLine = new ArrayList<>();

    public ButtonsForQuiz() {
        createButton();
    }

    public void attachKeyboard(SendMessage sendMessage) {
        sendMessage.setReplyMarkup(inlineKeyboardWithLanguage);
    }

    public void attachKeyboard(EditMessageText editMessageText) {
        editMessageText.setReplyMarkup(inlineKeyboardWithLanguage);
    }

    public void detachKeyboard(EditMessageText editMessageText) {
        editMessageText.setReplyMarkup(null);
    }

    private void createButton() {
        createButtonWithLanguages();
        inlineKeyboardWithLanguage.setKeyboard(rowsLine);
    }

    private void createButtonWithLanguages() {
        addButtonWithLanguage(ENGLISH.toString(), ENGLISH.getShortLanguageName(), ENGLISH.getFlagOfCountry());
        addButtonWithLanguage(GERMAN.toString(), GERMAN.getShortLanguageName(), GERMAN.getFlagOfCountry());
        addButtonWithLanguage(RUSSIAN.toString(), RUSSIAN.getShortLanguageName(), RUSSIAN.getFlagOfCountry());
        addRow();
    }

    private void addButtonWithLanguage(String languageName, String languageId, String flagOfCountry) {
        InlineKeyboardButton language = new InlineKeyboardButton();
        language.setText(languageName + " " + EmojiParser.parseToUnicode(flagOfCountry));
        language.setCallbackData(languageId + " " + QUIZ.getCommandName());

        ButtonsForQuiz.firstLine.add(language);
    }

    private void addRow() {
        rowsLine.add(ButtonsForQuiz.firstLine);
    }
}
