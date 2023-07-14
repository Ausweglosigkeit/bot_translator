package org.telegramBot.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.CommandContainer;
import org.telegramBot.service.SendBotMessageImpl;

import java.util.ResourceBundle;

import static org.telegramBot.command.CommandName.NO;

public class DarkZeit extends TelegramLongPollingBot {
    private static final ResourceBundle BOT_DATA = ResourceBundle.getBundle("org.telegramBot.bot.RegistrationData");
    private final CommandContainer commandContainer;

    public static final String COMMAND_PREFIX = "/";

    public DarkZeit() {
        commandContainer = new CommandContainer(new SendBotMessageImpl(this));
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
            }
            else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }
}