package org.telegramBot.command.message;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.translateAPI.Language;

import static org.telegramBot.command.CommandName.AVAILABLELANGUAGE;

public abstract class InformationAboutMessage {

    private static final Language[] languages = Language.values();
    protected static final String UNKNOWN_LANGUAGE = String.format("Вы неправильно ввели язык!\nЕсли вы хотите ввести его с клавиатуры," +
                    " то посмотрите поддерживаемые языки с помощью команды - %s",
                    AVAILABLELANGUAGE.getCommandName());

    protected String getLanguageTo(Update update) {
        if (update.hasMessage()) {
            String message = update.getMessage().getText();
            for (Language language : languages) {
                String shortLanguage = language.getShortLanguageName();
                if (shortLanguage.equals(message)) {
                    return shortLanguage;
                }
            }
            return null;

        } else {
            return getLanguage(update);
        }
    }

    protected String getLanguageFrom(Update update) {
        if (update.hasMessage()) {
            String message = update.getMessage().getText();
            for (Language language : languages) {
                String shortLanguage = language.getShortLanguageName();
                if (shortLanguage.equals(message)) {
                    return shortLanguage;
                }
            }
            return null;

        } else {
            return getLanguage(update);
        }
    }

    protected int getMessageId(Update update) {
        return update.getCallbackQuery().getMessage().getMessageId();
    }

    protected long getLongChatId(Update update) {
        return update.getCallbackQuery().getMessage().getChatId();
    }

    protected String getStringChatId(Update update) {
        if (update.hasMessage()){
            return update.getMessage().getChatId().toString();
        }
        else {
            return update.getCallbackQuery().getMessage().getChatId().toString();
        }
    }

    protected String getText(Update update) {
        return update.getMessage().getText();
    }

    protected Chat getChat(Update update) {
        return update.getMessage().getChat();
    }

    protected String getFirstName(Update update) {
        return getChat(update).getFirstName();
    }

    protected String getLastName(Update update) {
        return  getChat(update).getLastName();
    }

    private String getLanguage(Update update) {
        return update.getCallbackQuery().getData().split(" ")[0];
    }
}
