package org.telegramBot.command.container;

import com.google.common.collect.ImmutableMap;
import org.telegramBot.command.Command;

import static org.telegramBot.command.CommandName.*;

public class LastCommandContainer {
    private final ImmutableMap<String, Command> lastCommandImmutableMap;
    private final Command NoCommand;

    public LastCommandContainer(CommandContainer commandContainer) {
        lastCommandImmutableMap = ImmutableMap.<String, Command>builder()
                .put(TRANSLATE.getCommandName(), commandContainer.retrieveCommand(TRANSLATE.getCommandName()))
                .put(QUIZ.getCommandName(), commandContainer.retrieveCommand(QUIZ.getCommandName()))
                .build();

        NoCommand = commandContainer.retrieveCommand(NO.getCommandName());
    }

    public Command retrieveCommand(String lastCommand){
        return lastCommandImmutableMap.getOrDefault(lastCommand, NoCommand);
    }
}
