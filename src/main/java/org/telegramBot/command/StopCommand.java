package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.service.SendBotMessageService;

import static org.telegramBot.command.CommandName.START;

public class StopCommand extends InformationAboutMessage implements Command{
    private final SendBotMessageService sendBotMessageService;

    public static final String STOP_MESSAGE = "До скорой встречи, %s.\n"
            + "Вы всегда можете вернуться нажав %s";
    public static final String STOP_HELP = "Бот перестанет к вам приставать ;)";

    public StopCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

        String firstName = getFirstName(update);
        String lastName = getLastName(update);

        if(lastName == null) {
            sendBotMessageService.sendMessage(getStringChatId(update), String.format(STOP_MESSAGE, firstName, START.getCommandName()));
        }
        else {
            String userName = firstName + " " + lastName;
            sendBotMessageService.sendMessage(getStringChatId(update), String.format(STOP_MESSAGE, userName, START.getCommandName()));
        }
    }
}
