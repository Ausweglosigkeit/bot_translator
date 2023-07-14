package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.service.SendBotMessage;

import static org.telegramBot.command.CommandName.HELP;

public class UnknownCommand implements Command {
    private final SendBotMessage sendBotMessage;

    public static final String UNKNOWN_MESSAGE = String.format("Не понимаю тебя, напиши пожалуйста %s", HELP.getCommandName());

    public UnknownCommand(SendBotMessage sendBotMessage) {
        this.sendBotMessage = sendBotMessage;
    }

    @Override
    public void execute(Update update) {
        sendBotMessage.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }
}
