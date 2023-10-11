package org.telegramBot.command.subscribes;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.Command;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.command.notification.NotificationSchedule;
import org.telegramBot.service.SendBotMessageService;

import static org.telegramBot.command.CommandName.UNSUBSCRIBECAT;

public class UnsubscribeWordsCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;

    private static final String UNSUBSCRIBE_WORD_MESSAGE = "Вы успешно отписались от рассылки.";
    public static final String UNSUBSCRIBE_WORD_HELP = String.format("%s - отписка от рассылки бота.", UNSUBSCRIBECAT.getCommandName());


    public UnsubscribeWordsCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        NotificationSchedule.stopSendWordsNotification();
        sendBotMessageService.sendMessage(getStringChatId(update), UNSUBSCRIBE_WORD_MESSAGE);
    }
}
