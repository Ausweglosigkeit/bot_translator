package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.service.SendBotMessage;

import static org.telegramBot.command.CommandName.START;
import static sun.util.locale.LocaleUtils.isEmpty;

public class StopCommand implements Command{
    private final SendBotMessage sendBotMessage;

    public static final String STOP_MESSAGE = "До скорой встречи, %s.\n"
            + "Вы всегда можете вернуться нажав %s";
    public static final String STOP_HELP = "Бот перестанет к вам приставать ;)";

    public StopCommand(SendBotMessage sendBotMessage) {
        this.sendBotMessage = sendBotMessage;
    }

    @Override
    public void execute(Update update) {
        Chat dataOfUser = update.getMessage().getChat();

        String firstName = dataOfUser.getFirstName();
        String lastName = dataOfUser.getLastName();

        if(isEmpty(lastName)) {
            sendBotMessage.sendMessage(update.getMessage().getChatId().toString(), String.format(STOP_MESSAGE, firstName, START.getCommandName()));
        }
        else {
            String userName = firstName + " " + lastName;
            sendBotMessage.sendMessage(update.getMessage().getChatId().toString(), String.format(STOP_MESSAGE, userName, START.getCommandName()));
        }
    }
}
