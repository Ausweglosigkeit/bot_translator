package org.telegramBot.command.subscribes;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.Command;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.command.notification.Notification;
import org.telegramBot.command.notification.NotificationSchedule;
import org.telegramBot.service.SendBotMessageService;

import static org.telegramBot.command.CommandName.SUBSCRIBE;

public class SubscribeWordsCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;

    private static final String SUBSCRIBE_WORD_MESSAGE = "Вы только что подписались на рассылку от бота.";
    private static final String SUBSCRIBE_NOTIFICATION = "";
    public static final String SUBSCRIBE_WORD_HELP = String.format("%s - Я буду присылать вам раз в день слова на том языке, который вы выберите.", SUBSCRIBE.getCommandName());


    public SubscribeWordsCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getStringChatId(update), SUBSCRIBE_WORD_MESSAGE);
        new NotificationSchedule(sendBotMessageService, update, Notification.WORD);
    }
}
