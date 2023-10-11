package org.telegramBot.command;

public enum CommandName {
    START("/start"),
    STOP("/stop"),
    ABOUT("/about"),
    HELP("/help"),
    NO("nocommand"),
    ECHO("/echo"),
    TRANSLATE("/translate"),
    QUIZ("/quiz"),
    ENDQUIZ("/endquiz"),
    AVAILABLELANGUAGE("/avaiblelanguage"),
    SUBSCRIBE("/subscribetowords"),
    UNSUBSCRIBE("/unsubscribetowords"),
    SUBSCRIBECAT("/subscribetocats"),
    UNSUBSCRIBECAT("/unsubscribetocats"),
    REGISTRATION("/registration");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
