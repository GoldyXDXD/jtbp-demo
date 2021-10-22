package org.teamnescafe.jtbpdemo.command;

import com.google.common.collect.ImmutableMap;
import org.teamnescafe.jtbpdemo.service.*;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.teamnescafe.jtbpdemo.command.CommandName.*;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final List<String> admins;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, HomeworkService homeworkService, StudentService studentService, SubjectService subjectService, List<String> admins) {
        this.admins = admins;

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(ACTIVE_HOMEWORK.getCommandName(), new ActiveHomeworkCommand(sendBotMessageService, homeworkService))
                .put(HOMEWORK_HISTORY.getCommandName(), new AllHomeworkCommand(sendBotMessageService, homeworkService))
                .put(STUDENT_LIST.getCommandName(), new StudentListCommand(sendBotMessageService, studentService))
                .put(GROUP_TIMETABLE.getCommandName(), new GroupTimetableCommand(sendBotMessageService, subjectService))
                .put(TIMETABLE.getCommandName(), new TimetableCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

    private boolean isAdminCommand(Command command) {
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }

}
