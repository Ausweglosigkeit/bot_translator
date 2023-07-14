package org.telegramBot.command;

import com.google.common.collect.ImmutableMap;

import static org.telegramBot.command.AboutCommand.ABOUT_HELP;
import static org.telegramBot.command.CommandName.*;
import static org.telegramBot.command.EchoCommand.ECHO_HELP;
import static org.telegramBot.command.StartCommand.START_HELP;
import static org.telegramBot.command.StopCommand.STOP_HELP;

public class HelpForAllCommand {
    private final String unknownCommand = "Эту команду обделили HELP`ом";

    private final ImmutableMap<String, String> containerHelpForCommand = ImmutableMap.<String, String>builder()
            .put(START.getCommandName(), START_HELP)
            .put(ECHO.getCommandName(), ECHO_HELP)
            .put(STOP.getCommandName(), STOP_HELP)
            .put(ABOUT.getCommandName(), ABOUT_HELP)
            .build();

    public String getHelpForCommand(String commandName) {
        return containerHelpForCommand.getOrDefault(commandName, unknownCommand);
    }

}
