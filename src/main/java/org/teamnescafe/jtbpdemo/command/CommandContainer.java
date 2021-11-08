package org.teamnescafe.jtbpdemo.command;

import com.google.common.collect.ImmutableMap;
import org.teamnescafe.jtbpdemo.service.*;

import java.util.ArrayList;
import java.util.List;

import static org.teamnescafe.jtbpdemo.command.CommandName.*;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final List<String> admins;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, HomeworkService homeworkService, StudentService studentService, SubjectService subjectService) {
        admins = List.of("474715304");

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

    public Command retrieveCommand(String commandIdentifier, String chatId) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdmin(orDefault)) {
            if (admins.contains(chatId)) {
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return orDefault;
    }

    private boolean isAdmin(Command command) {
        if (command.isAdminCommand()) {
            return true;
        }
        return false;
    }
}
