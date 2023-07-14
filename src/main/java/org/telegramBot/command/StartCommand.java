package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.service.SendBotMessage;

import static org.telegramBot.command.CommandName.HELP;
import static org.telegramBot.command.CommandName.START;

public class StartCommand implements Command {
    private final SendBotMessage sendBotMessage;

    public static final String START_MESSAGE = "Здравствуйте, %s.\n"
            + "Я бот переводчик. И я вам постараюсь помочь в вашей нелёгкой жизни по изучению иностранного языка.\n"
            + "Пожалуйста напишите %s чтобы ознакомится с доступными командами.";;
    public static final String START_HELP = String.format("%s - начать работу со мной", START.getCommandName());;

    public StartCommand(SendBotMessage sendBotMessage) {
        this.sendBotMessage = sendBotMessage;
    }

    @Override
    public void execute(Update update) {
        Chat dataOfUser = update.getMessage().getChat();

        String firstName = dataOfUser.getFirstName();
        String lastName = dataOfUser.getLastName();

        if(lastName == null) {
            sendBotMessage.sendMessage(update.getMessage().getChatId().toString(), String.format(START_MESSAGE, firstName, HELP.getCommandName()));
        }
        else {
            String userName = firstName + " " + lastName;
            sendBotMessage.sendMessage(update.getMessage().getChatId().toString(), String.format(START_MESSAGE, userName, HELP.getCommandName()));
        }
    }
}
