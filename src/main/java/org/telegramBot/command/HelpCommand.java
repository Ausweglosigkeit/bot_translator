package org.telegramBot.command;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.command.container.HelpForAllCommandContainer;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.service.SendBotMessageService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.telegramBot.command.CommandName.*;

public class HelpCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;
    private static final String HELP_REGEX = "^(/help *) (/[a-z]+) *$";
    private static final HelpForAllCommandContainer HELP_FOR_ALL_COMMAND = new HelpForAllCommandContainer();

    public static final String HELP_MESSAGE = String.format(
            "Я бот переводчик и могу помочь тебе с переводом чего-либо.\n"
                    + "\n<b>Стартовые команды для взаимодействия с ботом:</b>\n"
                    + "%s - Начать работу со мной.\n"
                    + "%s - Получить помощь в работе со мной.\n"
                    + "%s - Получить информацию обо мне.\n"
                    + "%s - Повторю ваше сообщение как хрюшка-повторюшка.\n(напишите сообщение, которое нужно через пробел после данной команды)\n"
                    + "\n<b>Команды для перевода слов/фраз/предложений:</b>\n"
                    + "%s - Получить перевод слова/фразы/предложения.\n"
                    + "%s - Выведет поддерживаемые языки и их краткое название, если вы не захотите наживать на кнопочки.\n"
                    + "\n<b>Команды для игры в квиз:</b>\n"
                    + "%s - Буду играть с вами в КВИЗ.\n"
                    + "%s - Остановлю ваше развитие." + EmojiParser.parseToUnicode(":wink:\n")
                    + "\n<b>Команды для управления подпиской на слова:</b>\n"
                    + "%s - Я буду присылать вам раз в день слова на том языке, который вы выберите.\n"
                    + "%s - Отписка от рассылки бота.\n"
                    + "\n<b>Команды для управления подпиской на Котиков:</b>\n"
                    + "%s - Я буду вам надоедать и присылать вам разных Котеек.\n"
                    + "%s - Отписка от рассылки бота.",
            START.getCommandName(),
            HELP.getCommandName(),
            ABOUT.getCommandName(),
            ECHO.getCommandName(),
            TRANSLATE.getCommandName(),
            AVAILABLELANGUAGE.getCommandName(),
            QUIZ.getCommandName(),
            ENDQUIZ.getCommandName(),
            SUBSCRIBE.getCommandName(),
            UNSUBSCRIBE.getCommandName(),
            SUBSCRIBECAT.getCommandName(),
            UNSUBSCRIBECAT.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public void execute(Update update) {

        String message = getText(update);
        Pattern pattern = Pattern.compile(HELP_REGEX);
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            String commandName = matcher.group(2);
            String helpForCommand = HELP_FOR_ALL_COMMAND.getHelpForCommand(commandName);
            sendBotMessageService.sendMessage(getStringChatId(update), helpForCommand);
        }
        else {
            sendBotMessageService.sendMessage(getStringChatId(update), HELP_MESSAGE);
        }
    }
}
