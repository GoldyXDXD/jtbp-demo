package org.teamnescafe.jtbpdemo.command;

public enum CommandName {
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO(""),
    STAT("/stat"),
    ACTIVE_HOMEWORK("/active_homework"),
    HOMEWORK_HISTORY("/homework_history"),
    GROUP_TIMETABLE("/dgjadgjaokjgsdlkgdflkghdhkglkdfshgkdhghksldfkfhglkhdlkghdlkhghoipewrhgopierhgoiperhopiekhneokjoerigoierjgoierhgi"),
    TIMETABLE("/timetable"),
    STUDENT_LIST("/student_list");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
