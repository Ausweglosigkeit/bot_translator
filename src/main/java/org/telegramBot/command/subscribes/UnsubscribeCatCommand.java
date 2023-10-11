package org.telegramBot.command.subscribes;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.Command;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.command.notification.NotificationSchedule;
import org.telegramBot.service.SendBotMessageService;

import static org.telegramBot.command.CommandName.UNSUBSCRIBECAT;

public class UnsubscribeCatCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;

    private static final String UNSUBSCRIBE_CAT_MESSAGE = EmojiParser.parseToUnicode("Плак-плак хнык-хнык :cry:\nПочему ты отписался? :cry:\n");
    public static final String UNSUBSCRIBE_CAT_HELP = String.format("%s - отписка от рассылки бота.", UNSUBSCRIBECAT.getCommandName());

    public UnsubscribeCatCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        NotificationSchedule.stopSendCatsNotification();
        sendBotMessageService.sendMessage(getStringChatId(update), UNSUBSCRIBE_CAT_MESSAGE);
    }
}
