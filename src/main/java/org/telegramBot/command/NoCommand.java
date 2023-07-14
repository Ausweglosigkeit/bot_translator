package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.service.SendBotMessage;

public class NoCommand implements Command {
    private final SendBotMessage sendBotMessage;

    public static final String NO_MESSAGE = "К сожалению, я вас не понимаю. Все команды начинаются с \"/\".\n" +
            "Чтобы посмотреть список команд напишите /help.";;

    public NoCommand(SendBotMessage sendBotMessage) {
        this.sendBotMessage = sendBotMessage;
    }


    @Override
    public void execute(Update update) {
        sendBotMessage.sendMessage(update.getMessage().getChatId().toString(), NO_MESSAGE);
    }
}
