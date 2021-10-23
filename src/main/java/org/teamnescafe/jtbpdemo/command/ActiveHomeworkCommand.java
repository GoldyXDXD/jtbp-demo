package org.teamnescafe.jtbpdemo.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.teamnescafe.jtbpdemo.service.HomeworkService;
import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

public class ActiveHomeworkCommand implements Command {
    private final HomeworkService homeworkService;
    private final SendBotMessageService sendBotMessageService;

    public final static String NO_HOMEWORK_MESSAGE = "Домашки нет.";

    @Autowired
    public ActiveHomeworkCommand(SendBotMessageService sendBotMessageService, HomeworkService homeworkService) {
        this.sendBotMessageService = sendBotMessageService;
        this.homeworkService = homeworkService;
    }

    @Override
    public void execute(Update update) {
        int activeHomeworkCount = homeworkService.retrieveAllActiveHomework().size();
        if (activeHomeworkCount == 0) {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), NO_HOMEWORK_MESSAGE);
        } else {
            String homeworkString = homeworkService.retrieveAllActiveHomework().stream().map(String::valueOf).collect(Collectors.joining("\n"));
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), homeworkString);
        }
    }

    @Override
    public boolean isAdminCommand() {
        return false;
    }
}
