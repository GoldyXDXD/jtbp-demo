package org.teamnescafe.jtbpdemo.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.teamnescafe.jtbpdemo.service.StudentService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

public class StudentListCommand implements Command{

    public static final String NO_PEOPLE_MESSAGE = "В нашей группе никого нет, лул";

    private final SendBotMessageService sendBotMessageService;
    private final StudentService studentService;

    @Autowired
    public StudentListCommand(SendBotMessageService sendBotMessageService, StudentService studentService) {
        this.sendBotMessageService = sendBotMessageService;
        this.studentService = studentService;
    }

    @Override
    public void execute(Update update) {
        int studentCount = studentService.retrieveAll().size();
        if (studentCount == 0) {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), NO_PEOPLE_MESSAGE);
        } else {
            String studentList = studentService
                    .retrieveAll().stream().map(String::valueOf).collect(Collectors.joining("\n"));
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), studentList);
        }
    }
}
