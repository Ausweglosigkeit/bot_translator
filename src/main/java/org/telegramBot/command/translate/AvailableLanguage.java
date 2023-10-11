package org.telegramBot.command.translate;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.Command;
import org.telegramBot.service.SendBotMessageService;
import org.telegramBot.translateAPI.AvailableLanguages;

import static org.telegramBot.command.CommandName.AVAILABLELANGUAGE;

public class AvailableLanguage implements Command {
    private final SendBotMessageService sendBotMessageService;

    private static final String COMMAND_MESSAGE = String.format("<b>Языки, которые поддерживает бот:</b>\n\n%s", AvailableLanguages.getLanguages());
    public static final String COMMAND_HELP = String.format("%s - выведет поддерживаемые языки и их краткое название, если вы не захотите наживать на кнопочки", AVAILABLELANGUAGE.getCommandName());

    public AvailableLanguage(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), COMMAND_MESSAGE);
    }

    private String getChatId(Update update) {
        return update.getMessage().getChatId().toString();
    }
}
