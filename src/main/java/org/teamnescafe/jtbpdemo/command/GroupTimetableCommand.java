package org.teamnescafe.jtbpdemo.command;

import net.fortuna.ical4j.data.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.teamnescafe.jtbpdemo.service.SubjectService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.stream.Collectors;

class GroupTimetableCommand implements Command {

    private final SubjectService subjectService;
    private final SendBotMessageService sendBotMessageService;

    @Autowired
    GroupTimetableCommand(SendBotMessageService sendBotMessageService, SubjectService subjectService) {
        this.sendBotMessageService = sendBotMessageService;
        this.subjectService = subjectService;
    }

    @Override
    public void execute(Update update) throws IOException, ParserException {
        String subjectList = subjectService
                .retrieveAllByDate(update.getMessage().getText().trim()).stream().map(String::valueOf).collect(Collectors.joining("\n"));
        if (subjectList.isEmpty()) {
            subjectList = "Ничего не найдено.";
        }
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), subjectList);
    }

    @Override
    public boolean isAdminCommand() {
        return false;
    }
}


