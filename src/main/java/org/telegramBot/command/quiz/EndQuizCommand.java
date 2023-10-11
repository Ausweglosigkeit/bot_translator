package org.telegramBot.command.quiz;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.bot.DarkZeit;
import org.telegramBot.command.Command;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.service.SendBotMessageService;

import static org.telegramBot.command.CommandName.ENDQUIZ;

public class EndQuizCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String ENDQUIZ_MESSAGE = "Игра закончена! Вы молодчинка!\n\n Ваш результат правильно переведённых слов: %s.";
    public static final String ENDQUIZ_HELP = String.format(EmojiParser.parseToUnicode("%s - Остановлю ваше развитие. :wink:"), ENDQUIZ.getCommandName());

    public EndQuizCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        QuizCommand.cleanSTAGE();
        DarkZeit.cleanLastCommand();

        sendBotMessageService.sendMessage(getStringChatId(update), String.format(ENDQUIZ_MESSAGE, QuizCommand.getCorrectAnswers()));
        QuizCommand.cleanCorrectAnswers();
    }
}
