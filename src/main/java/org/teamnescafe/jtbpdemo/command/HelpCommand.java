package org.teamnescafe.jtbpdemo.command;

import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.teamnescafe.jtbpdemo.command.CommandName.*;

public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("✨<b>Доступные команды</b>✨\n\n"

                    + "<b>Начать/закончить работу с ботом</b>\n"
                    + "%s - начать работу со мной\n"
                    + "%s - уйти прочь (пригодится в дальнейшем для отключения уведомлений)\n"
                    + "%s - посмотреть архив домашних заданий\n"
                    + "%s - посмотреть актуальное домашнее задание\n"
                    + "%s - посмотреть расписание\n"
                    + "%s - посмотреть команды админа (доступно только администраторам)\n"
                    + "%s - посмотреть список группы\n\n"
                    + "%s - для тех, кому не хватает ПОМАЩИ\n",
            START.getCommandName(), STOP.getCommandName(),HOMEWORK_HISTORY.getCommandName(), ACTIVE_HOMEWORK.getCommandName(), TIMETABLE.getCommandName(), ADMIN_HELP.getCommandName(), STUDENT_LIST.getCommandName() ,HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }

    @Override
    public boolean isAdminCommand() {
        return false;
    }
}