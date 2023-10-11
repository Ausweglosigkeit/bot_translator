package org.telegramBot.command.translate;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.button.ButtonsForTranslation;
import org.telegramBot.command.Command;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.service.SendBotMessageService;
import org.telegramBot.translateAPI.Translator;

import static org.telegramBot.command.CommandName.TRANSLATE;

public class TranslateCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final EditMessageText editMessageText = new EditMessageText();
    private final ButtonsForTranslation buttonsForTranslation = new ButtonsForTranslation();

    private static short STAGE = 0;
    private static String LANGUAGE_TO;
    private static String LANGUAGE_FROM;

    public static final String TRANSLATE_MESSAGE = "Перевод слова/фразы/предложения:\n %s";
    public static final String TRANSLATE_SHORT_HELP = String.format(EmojiParser.parseToUnicode("%s - перевод слова/фразы/предложения, какое только вашей душе угодно. :wink:"), TRANSLATE.getCommandName());

    public TranslateCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

        switch (STAGE) {
            case 0 -> {
                STAGE++;

                SendMessage sendMessage = new SendMessage();
                String textMessage = "Выберите язык, с которого хотите перевести слово/фразу/предложение.";

                buttonsForTranslation.attachKeyboard(sendMessage);
                sendBotMessageService.sendMessageWithInlineButton(sendMessage, getStringChatId(update), textMessage);
            }
            case 1 -> {
                LANGUAGE_FROM = getLanguageFrom(update);
                if (LANGUAGE_FROM == null) {
                    STAGE = 0;
                    sendBotMessageService.sendMessage(getStringChatId(update), UNKNOWN_LANGUAGE);
                    return;
                }
                STAGE++;

                String textMessage = "Выберите язык, на который хотите перевести слово/фразу/предложения.";

                buttonsForTranslation.attachKeyboard(editMessageText);
                sendBotMessageService.sendEditMessageText(editMessageText, getLongChatId(update), getMessageId(update), textMessage);
            }
            case 2 -> {
                LANGUAGE_TO = getLanguageTo(update);
                if (LANGUAGE_TO == null) {
                    STAGE = 0;
                    sendBotMessageService.sendMessage(getStringChatId(update), UNKNOWN_LANGUAGE);
                    return;
                }

                STAGE++;

                String textMessage = "Напишите слово/фразу/предложение которое хотите перевести.";

                buttonsForTranslation.detachKeyboard(editMessageText);
                sendBotMessageService.sendEditMessageText(editMessageText, getLongChatId(update), getMessageId(update), textMessage);
            }
            default -> {
                STAGE = 0;

                Translator translator = new Translator();
                String translatedText = translator.translateRequest(getTextToTranslate(update), LANGUAGE_TO, LANGUAGE_FROM);

                sendBotMessageService.sendMessage(getStringChatId(update), String.format(TRANSLATE_MESSAGE, translatedText));
            }
        }
    }

    private String getTextToTranslate(Update update) {
        return update.getMessage().getText().trim();
    }
}
