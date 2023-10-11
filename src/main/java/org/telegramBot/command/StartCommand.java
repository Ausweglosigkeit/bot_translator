package org.telegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.service.SendBotMessageService;

import static org.telegramBot.command.CommandName.HELP;
import static org.telegramBot.command.CommandName.START;

public class StartCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String START_MESSAGE = "Здравствуйте, %s.\n"
            + "Я бот переводчик. И я вам постараюсь помочь в вашей нелёгкой жизни по изучению иностранного языка.\n"
            + "Пожалуйста напишите %s чтобы ознакомится с доступными командами.";;
    public static final String START_HELP = String.format("%s - начать работу со мной", START.getCommandName());;

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

        String firstName = getFirstName(update);
        String lastName = getLastName(update);

        if(lastName == null) {
            sendBotMessageService.sendMessage(getStringChatId(update), String.format(START_MESSAGE, firstName, HELP.getCommandName()));
        }
        else {
            String userName = firstName + " " + lastName;
            sendBotMessageService.sendMessage(getStringChatId(update), String.format(START_MESSAGE, userName, HELP.getCommandName()));
        }
    }
}
