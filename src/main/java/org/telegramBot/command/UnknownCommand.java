package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.service.SendBotMessageService;

import static org.telegramBot.command.CommandName.HELP;

public class UnknownCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String UNKNOWN_MESSAGE = String.format("Не понимаю тебя, напиши пожалуйста %s", HELP.getCommandName());

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getStringChatId(update), UNKNOWN_MESSAGE);
    }
}
