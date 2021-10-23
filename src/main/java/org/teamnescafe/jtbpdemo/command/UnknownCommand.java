package org.teamnescafe.jtbpdemo.command;

import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command {

    public static final String UNKNOWN_MESSAGE = "Тяжелый случай... Ну ничего, для таких, как ты, есть команда /help";

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }

    @Override
    public boolean isAdminCommand() {
        return false;
    }
}
