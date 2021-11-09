package org.teamnescafe.jtbpdemo.command;

import net.fortuna.ical4j.data.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.teamnescafe.jtbpdemo.entity.Homework;
import org.teamnescafe.jtbpdemo.entity.Student;
import org.teamnescafe.jtbpdemo.service.HomeworkService;
import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.teamnescafe.jtbpdemo.service.StudentService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.sql.Date;

public class ComplementDBCommand implements Command{

    private final HomeworkService homeworkService;
    private final SendBotMessageService sendBotMessageService;
    private final StudentService studentService;

    public final static String UNKNOWN_KEYWORD = "Непонятное ключевое слово (на данный момент доступны СС и ДЗ)";
    public final static String SUCCESS_MESSAGE = "ДБ изменен!";

    @Autowired
    public ComplementDBCommand(HomeworkService homeworkService, SendBotMessageService sendBotMessageService, StudentService studentService) {
        this.homeworkService = homeworkService;
        this.sendBotMessageService = sendBotMessageService;
        this.studentService = studentService;
    }

    @Override
    public void execute(Update update) throws IOException, ParserException {
        String message = update.getMessage().getText().trim();
        String chatId = update.getMessage().getChatId().toString();
        switch (message.substring(0,2).toLowerCase()) {
            case "сс":
                String[] parameters = message.substring(3, message.length()).split(" ");
                Student newStudent = new Student();
                newStudent.setFirstAndLastName(parameters[0].replace('_', ' '));
                newStudent.setBirthday(Date.valueOf(parameters[1]));
                studentService.save(newStudent);
                break;
            case "дз":
                Homework newHomework = new Homework();
                newHomework.setDescription(message.trim().substring(3, message.length() - 1));
                homeworkService.save(newHomework);
                break;
            default:
                sendBotMessageService.sendMessage(chatId, UNKNOWN_KEYWORD);
        }
        sendBotMessageService.sendMessage(chatId, SUCCESS_MESSAGE);
    }

    @Override
    public boolean isAdminCommand() {
        return true;
    }
}
