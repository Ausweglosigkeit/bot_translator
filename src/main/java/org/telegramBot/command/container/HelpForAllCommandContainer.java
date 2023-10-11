package org.telegramBot.command.container;

import com.google.common.collect.ImmutableMap;

import static org.telegramBot.command.AboutCommand.ABOUT_HELP;
import static org.telegramBot.command.subscribes.SubscribeWordsCommand.SUBSCRIBE_WORD_HELP;
import static org.telegramBot.command.subscribes.UnsubscribeWordsCommand.UNSUBSCRIBE_WORD_HELP;
import static org.telegramBot.command.translate.AvailableLanguage.COMMAND_HELP;
import static org.telegramBot.command.CommandName.*;
import static org.telegramBot.command.EchoCommand.ECHO_HELP;
import static org.telegramBot.command.quiz.EndQuizCommand.ENDQUIZ_HELP;
import static org.telegramBot.command.quiz.QuizCommand.QUIZ_HELP;
import static org.telegramBot.command.StartCommand.START_HELP;
import static org.telegramBot.command.StopCommand.STOP_HELP;
import static org.telegramBot.command.subscribes.SubscribeCatCommand.SUBSCRIBE_CAT_HELP;
import static org.telegramBot.command.translate.TranslateCommand.TRANSLATE_SHORT_HELP;
import static org.telegramBot.command.subscribes.UnsubscribeCatCommand.UNSUBSCRIBE_CAT_HELP;

public class HelpForAllCommandContainer {
    private final ImmutableMap<String, String> containerHelpForCommand;
    private final String unknownCommand = "Эту команду обделили HELP`ом";

    public HelpForAllCommandContainer() {
        containerHelpForCommand = ImmutableMap.<String, String>builder()
                .put(START.getCommandName(), START_HELP)
                .put(ECHO.getCommandName(), ECHO_HELP)
                .put(STOP.getCommandName(), STOP_HELP)
                .put(ABOUT.getCommandName(), ABOUT_HELP)
                .put(TRANSLATE.getCommandName(), TRANSLATE_SHORT_HELP)
                .put(QUIZ.getCommandName(), QUIZ_HELP)
                .put(ENDQUIZ.getCommandName(), ENDQUIZ_HELP)
                .put(AVAILABLELANGUAGE.getCommandName(), COMMAND_HELP)
                .put(SUBSCRIBE.getCommandName(), SUBSCRIBE_WORD_HELP)
                .put(UNSUBSCRIBE.getCommandName(), UNSUBSCRIBE_WORD_HELP)
                .put(SUBSCRIBECAT.getCommandName(), SUBSCRIBE_CAT_HELP)
                .put(UNSUBSCRIBECAT.getCommandName(), UNSUBSCRIBE_CAT_HELP)
                .build();
    }

    public String getHelpForCommand(String commandName) {
        return containerHelpForCommand.getOrDefault(commandName, unknownCommand);
    }
}
