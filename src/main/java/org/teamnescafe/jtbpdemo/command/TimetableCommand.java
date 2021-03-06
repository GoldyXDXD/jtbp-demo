package org.teamnescafe.jtbpdemo.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TimetableCommand implements Command{
    public static final String TIMETABLE_MESSAGE = "Введи дату в формате yyyy-mm-dd:";

    private final SendBotMessageService sendBotMessageService;

    @Autowired
    public TimetableCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), TIMETABLE_MESSAGE);
    }

    @Override
    public boolean isAdminCommand() {
        return false;
    }
}
