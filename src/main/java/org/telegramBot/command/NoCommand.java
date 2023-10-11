package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.service.SendBotMessageService;

public class NoCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String NO_MESSAGE = "К сожалению, я вас не понимаю. Все команды начинаются с \"/\".\n" +
            "Чтобы посмотреть список команд напишите /help.";;

    public NoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getStringChatId(update), NO_MESSAGE);
    }
}
