package org.telegramBot.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.container.CommandContainer;
import org.telegramBot.command.container.LastCommandContainer;
import org.telegramBot.service.SendBotMessageServiceImpl;

import java.util.ResourceBundle;

public class DarkZeit extends TelegramLongPollingBot {
    private static String LAST_COMMAND;
    private final CommandContainer commandContainer;
    private final LastCommandContainer lastCommandContainer;

    public static final String COMMAND_PREFIX = "/";
    public static final ResourceBundle BOT_DATA = ResourceBundle.getBundle("org.telegramBot.bot.RegistrationData");

    public DarkZeit() {
        commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
        lastCommandContainer = new LastCommandContainer(commandContainer);
    }

    @Override
    public String getBotUsername() {
        return BOT_DATA.getString("bot.username");
    }

    @Override
    public String getBotToken() {
        return BOT_DATA.getString("bot.token");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
                LAST_COMMAND = commandIdentifier;
            } else {
                lastCommandContainer.retrieveCommand(LAST_COMMAND).execute(update);
            }
        } else if (update.hasCallbackQuery()) {
            String commandIdentifier = update.getCallbackQuery().getData().split(" ")[1].toLowerCase();
            commandContainer.retrieveCommand(commandIdentifier).execute(update);
        }
    }

    public static void cleanLastCommand() {
        LAST_COMMAND = "";
    }
}
