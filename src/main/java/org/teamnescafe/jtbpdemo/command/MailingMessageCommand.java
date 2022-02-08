package org.teamnescafe.jtbpdemo.command;

import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MailingMessageCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public final static String WRITE_MESSAGE_4_GUYS = "Введи сообщение для рассылки";

    public MailingMessageCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), WRITE_MESSAGE_4_GUYS);
    }

    @Override
    public boolean isAdminCommand() {
        return true;
    }
}