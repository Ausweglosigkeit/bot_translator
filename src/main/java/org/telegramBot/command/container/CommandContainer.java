package org.telegramBot.command.container;

import com.google.common.collect.ImmutableMap;
import org.telegramBot.command.*;
import org.telegramBot.command.quiz.EndQuizCommand;
import org.telegramBot.command.quiz.QuizCommand;
import org.telegramBot.command.subscribes.SubscribeCatCommand;
import org.telegramBot.command.subscribes.SubscribeWordsCommand;
import org.telegramBot.command.subscribes.UnsubscribeCatCommand;
import org.telegramBot.command.subscribes.UnsubscribeWordsCommand;
import org.telegramBot.command.translate.AvailableLanguage;
import org.telegramBot.command.translate.TranslateCommand;
import org.telegramBot.service.SendBotMessageService;

import static org.telegramBot.command.CommandName.*;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandImmutableMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandImmutableMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(ECHO.getCommandName(), new EchoCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(ABOUT.getCommandName(), new AboutCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(TRANSLATE.getCommandName(), new TranslateCommand(sendBotMessageService))
                .put(QUIZ.getCommandName(), new QuizCommand(sendBotMessageService))
                .put(ENDQUIZ.getCommandName(), new EndQuizCommand(sendBotMessageService))
                .put(AVAILABLELANGUAGE.getCommandName(), new AvailableLanguage(sendBotMessageService))
                .put(SUBSCRIBE.getCommandName(), new SubscribeWordsCommand(sendBotMessageService))
                .put(UNSUBSCRIBE.getCommandName(), new UnsubscribeWordsCommand(sendBotMessageService))
                .put(SUBSCRIBECAT.getCommandName(), new SubscribeCatCommand(sendBotMessageService))
                .put(UNSUBSCRIBECAT.getCommandName(), new UnsubscribeCatCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandImmutableMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
