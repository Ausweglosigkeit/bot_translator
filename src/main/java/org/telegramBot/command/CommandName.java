package org.telegramBot.command;

public enum CommandName {
    START("/start"),
    STOP("/stop"),
    ABOUT("/about"),
    HELP("/help"),
    NO("nocommand"),
    ECHO("/echo");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
