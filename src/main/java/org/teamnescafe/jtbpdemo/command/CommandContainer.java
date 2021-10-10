package org.teamnescafe.jtbpdemo.command;

import com.google.common.collect.ImmutableMap;
import org.teamnescafe.jtbpdemo.service.HomeworkService;
import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.teamnescafe.jtbpdemo.service.StudentService;
import org.teamnescafe.jtbpdemo.service.TelegramUserService;

import static org.teamnescafe.jtbpdemo.command.CommandName.*;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, HomeworkService homeworkService, StudentService studentService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(ACTIVE_HOMEWORK.getCommandName(), new ActiveHomeworkCommand(sendBotMessageService, homeworkService))
                .put(HOMEWORK_HISTORY.getCommandName(), new AllHomeworkCommand(sendBotMessageService, homeworkService))
                .put(STUDENT_LIST.getCommandName(), new StudentListCommand(sendBotMessageService, studentService))
                .put(TIMETABLE.getCommandName(), new GroupTimetableCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
