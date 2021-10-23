package org.teamnescafe.jtbpdemo.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.teamnescafe.jtbpdemo.service.HomeworkService;
import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

public class AllHomeworkCommand implements Command {
    private final HomeworkService homeworkService;
    private final SendBotMessageService sendBotMessageService;

    public final static String NO_HOMEWORK_HISTORY_MESSAGE = "Домашки никогда не было.";

    @Autowired
    public AllHomeworkCommand(SendBotMessageService sendBotMessageService, HomeworkService homeworkService) {
        this.sendBotMessageService = sendBotMessageService;
        this.homeworkService = homeworkService;
    }

    @Override
    public void execute(Update update) {
        int allHomeworkCount = homeworkService.retrieveAllHomework().size();
        if (allHomeworkCount == 0) {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), NO_HOMEWORK_HISTORY_MESSAGE);
        } else {
            String homeworkString = homeworkService.retrieveAllHomework().stream().map(String::valueOf).collect(Collectors.joining("\n"));
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), homeworkString);
        }
    }
    @Override
    public boolean isAdminCommand() {
        return false;
    }
}
