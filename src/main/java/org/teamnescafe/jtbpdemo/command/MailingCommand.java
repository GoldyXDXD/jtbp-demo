package org.teamnescafe.jtbpdemo.command;

import org.teamnescafe.jtbpdemo.entity.TelegramUser;
import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.teamnescafe.jtbpdemo.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MailingCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String CHOOSE_DB_MESSAGE = "Введи сообщение для рассылки";

    public MailingCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String message = update.getMessage().getText().trim();
        for (TelegramUser user: telegramUserService.retrieveAllActiveUsers()) {
            sendBotMessageService.sendMessage(user.getChatId(), message);
        }
    }

    @Override
    public boolean isAdminCommand() {
        return true;
    }
}
