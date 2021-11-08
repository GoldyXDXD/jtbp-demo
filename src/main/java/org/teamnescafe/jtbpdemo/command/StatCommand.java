package org.teamnescafe.jtbpdemo.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.teamnescafe.jtbpdemo.entity.TelegramUser;
import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.teamnescafe.jtbpdemo.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatCommand implements Command {

    private boolean isAdminCommand = true;
    private final TelegramUserService telegramUserService;
    private final SendBotMessageService sendBotMessageService;

    public final static String STAT_MESSAGE_FOR_ONE_MAN = "Данный бот обслуживает 1 человека.";
    public final static String STAT_MESSAGE = "Данный бот обслуживает %s человек.";
    public final static String ACTIVE_STAT_MESSAGE = "Активных: ";

    @Autowired
    public StatCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        int activeUserCount = telegramUserService.retrieveAllActiveUsers().size();
        if (activeUserCount == 1) {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STAT_MESSAGE_FOR_ONE_MAN);
        } else {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), String.format(STAT_MESSAGE, activeUserCount));
            for (TelegramUser user: telegramUserService.retrieveAllUsers()) {
                sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), user.getChatId());
            }
        }
    }

    @Override
    public boolean isAdminCommand() {
        return true;
    }
}