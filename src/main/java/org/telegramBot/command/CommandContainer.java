package org.telegramBot.command;

import com.google.common.collect.ImmutableMap;
import org.telegramBot.service.SendBotMessage;

import static org.telegramBot.command.CommandName.*;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandImmutableMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessage sendBotMessage) {
        commandImmutableMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessage))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessage))
                .put(ECHO.getCommandName(), new EchoCommand(sendBotMessage))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessage))
                .put(ABOUT.getCommandName(), new AboutCommand(sendBotMessage))
                .put(NO.getCommandName(), new NoCommand(sendBotMessage))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessage);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandImmutableMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
