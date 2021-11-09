package org.teamnescafe.jtbpdemo.command;

import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CDBCMessageCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public final static String CHOOSE_DB_MESSAGE = "Выбери БД для изменения (CC, ДЗ) и параметры (команды и сопутствующие данные):\nP.S. ДЗ - домашнее задание, СС - список студентов\n"
            + "Примеры:\nСС Борзенков_Никита 2001-05-03\nДЗ Реферат по экономике"
            + "Формат ввода даты: yyyy-mm-dd, вывода: dd.mm.yyyy";

    public CDBCMessageCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), CHOOSE_DB_MESSAGE);
    }

    @Override
    public boolean isAdminCommand() {
        return false;
    }
}