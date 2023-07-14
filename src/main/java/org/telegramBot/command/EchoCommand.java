package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.service.SendBotMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.telegramBot.command.CommandName.ECHO;
import static org.telegramBot.command.CommandName.HELP;
import static sun.util.locale.LocaleUtils.isEmpty;

public class EchoCommand implements Command{
    private final SendBotMessage sendBotMessage;

    public static final String ECHO_MESSAGE = "Вы попросили повторить:\n \"%s\"";
    public static final String EMPTY_MESSAGE = "Вы ничего не написали.\nПожалуйста напишите команду %s чтобы узнать доступные команды.";
    public static final String ECHO_HELP = String.format("%s - повторю ваше сообщение как хрюшка-повторюшка", ECHO.getCommandName());

    public EchoCommand(SendBotMessage sendBotMessage) {
        this.sendBotMessage = sendBotMessage;
    }

    @Override
    public void execute(Update update) {
        String message = update.getMessage().getText();

        if (message.trim().equals(ECHO.getCommandName())) {
            sendBotMessage.sendMessage(update.getMessage().getChatId().toString(), String.format(EMPTY_MESSAGE, HELP.getCommandName()));
        }
        else {
            Pattern pattern = Pattern.compile("[^/echo]([\\s\\S]*)");
            Matcher matcher = pattern.matcher(message);

            matcher.find();
            sendBotMessage.sendMessage(update.getMessage().getChatId().toString(), String.format(ECHO_MESSAGE, matcher.group().trim()));
        }
    }
}
