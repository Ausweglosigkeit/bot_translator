package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.service.SendBotMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.telegramBot.command.CommandName.*;

public class HelpCommand implements Command {
    private final SendBotMessage sendBotMessage;

    public static final String HELP_MESSAGE = String.format("<b>Доступные команды:</b>\n\n"
                    + "%s - начать работу со мной\n"
                    + "%s - получить помощь в работе со мной\n"
                    + "%s - получить информацию обо мне\n"
                    + "%s - повторю ваше сообщение как хрюшка-повторюшка\n",
            START.getCommandName(),
            HELP.getCommandName(),
            ABOUT.getCommandName(),
            ECHO.getCommandName());

    public HelpCommand(SendBotMessage sendBotMessage) {
        this.sendBotMessage = sendBotMessage;
    }


    @Override
    public void execute(Update update) {

        String message = update.getMessage().getText();
        Pattern pattern = Pattern.compile("^(/help *) (/[a-z]+) *$");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            String commandName = matcher.group(2);
            String helpForCommand = new HelpForAllCommand().getHelpForCommand(commandName);
            sendBotMessage.sendMessage(update.getMessage().getChatId().toString(), helpForCommand);
        }
        else {
            sendBotMessage.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
        }
    }
}
