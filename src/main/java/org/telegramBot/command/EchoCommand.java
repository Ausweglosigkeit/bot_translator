package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.service.SendBotMessageService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.telegramBot.command.CommandName.ECHO;
import static org.telegramBot.command.CommandName.HELP;

public class EchoCommand extends InformationAboutMessage implements Command{
    private final SendBotMessageService sendBotMessageService;
    private static final String ECHO_REGEX = "[^/echo]([\\s\\S]*)";

    public static final String ECHO_MESSAGE = "Вы попросили повторить:\n \"%s\"";
    public static final String EMPTY_MESSAGE = "Вы ничего не написали.\nПожалуйста напишите команду %s чтобы узнать доступные команды.";
    public static final String ECHO_HELP = String.format("%s - повторю ваше сообщение как хрюшка-повторюшка", ECHO.getCommandName());

    public EchoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        String message = getText(update);

        if (message.trim().equals(ECHO.getCommandName())) {
            sendBotMessageService.sendMessage(getStringChatId(update), String.format(EMPTY_MESSAGE, HELP.getCommandName()));
        }
        else {
            Pattern pattern = Pattern.compile(ECHO_REGEX);
            Matcher matcher = pattern.matcher(message);

            if (matcher.find()) {
                sendBotMessageService.sendMessage(getStringChatId(update), String.format(ECHO_MESSAGE, matcher.group().trim()));
            }
        }
    }
}
