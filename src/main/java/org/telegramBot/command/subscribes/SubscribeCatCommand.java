package org.telegramBot.command.subscribes;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.Command;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.command.notification.Notification;
import org.telegramBot.command.notification.NotificationSchedule;
import org.telegramBot.service.SendBotMessageService;

import static org.telegramBot.command.CommandName.SUBSCRIBECAT;

public class SubscribeCatCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;

    private static final String SUBSCRIBE_CAT_MESSAGE = "Вы только что подписались на мимишную рассылку от бота.";
    private static final String SUBSCRIBE_NOTIFICATION = "";
    public static final String SUBSCRIBE_CAT_HELP = String.format("%s - Я буду вам надоедать и присылать вам разных Котеек.", SUBSCRIBECAT.getCommandName());

    public SubscribeCatCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getStringChatId(update), SUBSCRIBE_CAT_MESSAGE);
        new NotificationSchedule(sendBotMessageService, update, Notification.CAT);
    }
}
