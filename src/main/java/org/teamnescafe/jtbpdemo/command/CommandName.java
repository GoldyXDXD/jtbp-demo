package org.teamnescafe.jtbpdemo.command;

public enum CommandName {
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO(""),
    STAT("/stat"),
    ACTIVE_HOMEWORK("/active_homework"),
    HOMEWORK_HISTORY("/homework_history");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
