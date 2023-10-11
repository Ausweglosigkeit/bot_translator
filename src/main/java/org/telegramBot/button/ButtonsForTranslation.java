package org.telegramBot.button;


import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.telegramBot.command.CommandName.TRANSLATE;
import static org.telegramBot.translateAPI.Language.*;


public class ButtonsForTranslation {
    private static final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private static final List<List<InlineKeyboardButton>> rowsLine = new ArrayList<>();
    private static final List<InlineKeyboardButton> firstLine = new ArrayList<>();
    private static final List<InlineKeyboardButton> secondLine = new ArrayList<>();
    private static final List<InlineKeyboardButton> thirdLine = new ArrayList<>();
    private static final List<InlineKeyboardButton> fourthLine = new ArrayList<>();
    private static final List<InlineKeyboardButton> fifthLine = new ArrayList<>();
    private static final List<InlineKeyboardButton> sixthLine = new ArrayList<>();

    public ButtonsForTranslation() {
        createButton();
    }

    public void attachKeyboard(SendMessage sendMessage) {
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }

    public void attachKeyboard(EditMessageText editMessageText) {
        editMessageText.setReplyMarkup(inlineKeyboardMarkup);
    }

    public void detachKeyboard(EditMessageText editMessageText) {
        editMessageText.setReplyMarkup(null);
    }

    private void createButton() {
        createButtonWithLanguages();
        inlineKeyboardMarkup.setKeyboard(rowsLine);
    }

    private void createButtonWithLanguages() {
        addButtonWithLanguage(ENGLISH.toString(), ENGLISH.getShortLanguageName(), ENGLISH.getFlagOfCountry(), firstLine);
        addButtonWithLanguage(GERMAN.toString(), GERMAN.getShortLanguageName(), GERMAN.getFlagOfCountry(), firstLine);
        addButtonWithLanguage(ITALIAN.toString(), ITALIAN.getShortLanguageName(), ITALIAN.getFlagOfCountry(), firstLine);
        addRow(firstLine);

        addButtonWithLanguage(RUSSIAN.toString(), RUSSIAN.getShortLanguageName(), RUSSIAN.getFlagOfCountry(), secondLine);
        addButtonWithLanguage(KAZAKH.toString(), KAZAKH.getShortLanguageName(), KAZAKH.getFlagOfCountry(), secondLine);
        addButtonWithLanguage(SLOVAK.toString(), SLOVAK.getShortLanguageName(), SLOVAK.getFlagOfCountry(), secondLine);
        addRow(secondLine);

        addButtonWithLanguage(POLISH.toString(), POLISH.getShortLanguageName(), POLISH.getFlagOfCountry(), thirdLine);
        addButtonWithLanguage(UKRAINIAN.toString(), UKRAINIAN.getShortLanguageName(), UKRAINIAN.getFlagOfCountry(), thirdLine);
        addButtonWithLanguage(SPANISH.toString(), SPANISH.getShortLanguageName(), SPANISH.getFlagOfCountry(), thirdLine);
        addRow(thirdLine);

        addButtonWithLanguage(NORWEGIAN.toString(), NORWEGIAN.getShortLanguageName(), NORWEGIAN.getFlagOfCountry(), fourthLine);
        addButtonWithLanguage(JAPANESE.toString(), JAPANESE.getShortLanguageName(), JAPANESE.getFlagOfCountry(), fourthLine);
        addButtonWithLanguage(FRENCH.toString(), FRENCH.getShortLanguageName(), FRENCH.getFlagOfCountry(), fourthLine);
        addRow(fourthLine);

        addButtonWithLanguage(ALBANIAN.toString(), ALBANIAN.getShortLanguageName(), ALBANIAN.getFlagOfCountry(), fifthLine);
        addButtonWithLanguage(ARMENIAN.toString(), ARMENIAN.getShortLanguageName(), ARMENIAN.getFlagOfCountry(), fifthLine);
        addButtonWithLanguage(DUTCH.toString(), DUTCH.getShortLanguageName(), DUTCH.getFlagOfCountry(), fifthLine);
        addRow(fifthLine);

        addButtonWithLanguage(SWEDISH.toString(), SWEDISH.getShortLanguageName(), SWEDISH.getFlagOfCountry(), sixthLine);
        addButtonWithLanguage(TURKISH.toString(), TURKISH.getShortLanguageName(), TURKISH.getFlagOfCountry(), sixthLine);
        addButtonWithLanguage(FINNISH.toString(), FINNISH.getShortLanguageName(), FINNISH.getFlagOfCountry(), sixthLine);
        addRow(sixthLine);
    }

    private void addButtonWithLanguage(String languageName, String languageId, String flagOfCountry, List<InlineKeyboardButton> row) {
        InlineKeyboardButton language = new InlineKeyboardButton();
        language.setText(languageName + " " + EmojiParser.parseToUnicode(flagOfCountry));
        language.setCallbackData(languageId + " " + TRANSLATE.getCommandName());

        row.add(language);
    }

    private void addRow(List<InlineKeyboardButton> row) {
        rowsLine.add(row);
    }
}
