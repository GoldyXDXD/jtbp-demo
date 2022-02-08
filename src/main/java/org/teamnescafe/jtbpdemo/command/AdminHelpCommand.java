package org.teamnescafe.jtbpdemo.command;

import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static java.lang.String.format;
import static org.teamnescafe.jtbpdemo.command.CommandName.*;

public class AdminHelpCommand implements Command {

    public static final String ADMIN_HELP_MESSAGE = format("✨<b>Доступные команды админа</b>✨\n\n"
                    + "%s - добавление записей в БД\n"
                    + "%s - статистика бота\n"
                    + "%s - ровести рассылку\n",
            COMPLEMENT_DB.getCommandName(), STAT.getCommandName(), MAILING_MESSAGE.getCommandName());

    private final SendBotMessageService sendBotMessageService;

    public AdminHelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), ADMIN_HELP_MESSAGE);
    }

    @Override
    public boolean isAdminCommand() {
        return true;
    }
}