package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.service.SendBotMessageService;

import static org.telegramBot.command.CommandName.ABOUT;

public class AboutCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String ABOUT_MESSAGE = "Я бот, который поможет вам с переводом слов/фраз.\n";
    public static final String ABOUT_HELP = String.format("%s - получить информацию обо мне. Для чего я был создан судьбой", ABOUT.getCommandName());

    public AboutCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getStringChatId(update), ABOUT_MESSAGE);
    }
}
