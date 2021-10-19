package org.teamnescafe.jtbpdemo.bot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.teamnescafe.jtbpdemo.command.CommandContainer;
import org.teamnescafe.jtbpdemo.service.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.teamnescafe.jtbpdemo.command.CommandName.NO;
import static org.teamnescafe.jtbpdemo.command.CommandName.TIMETABLE;

@Component
public class JavaTelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    public JavaTelegramBot(TelegramUserService telegramUserService, HomeworkService homeworkService, StudentService studentService, SubjectService subjectService) {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService, homeworkService, studentService, subjectService);
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            Pattern pattern = Pattern.compile("[0-9][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9]");
            Matcher matcher = pattern.matcher(message);
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else if (matcher.matches()) {
                commandContainer.retrieveCommand(TIMETABLE.getCommandName()).execute(update);
            }
            else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}