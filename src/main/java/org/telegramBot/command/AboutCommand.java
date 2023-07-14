package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.service.SendBotMessage;

import static org.telegramBot.command.CommandName.ABOUT;

public class AboutCommand implements Command {
    private final SendBotMessage sendBotMessage;

    public static final String ABOUT_MESSAGE = "Я бот, который поможет вам с переводом слов/фраз.\n";
    public static final String ABOUT_HELP = String.format("%s - получить информацию обо мне. Для чего я был создан судьбой", ABOUT.getCommandName());

    public AboutCommand(SendBotMessage sendBotMessage) {
        this.sendBotMessage = sendBotMessage;
    }

    @Override
    public void execute(Update update) {
        sendBotMessage.sendMessage(update.getMessage().getChatId().toString(), ABOUT_MESSAGE);
    }
}
