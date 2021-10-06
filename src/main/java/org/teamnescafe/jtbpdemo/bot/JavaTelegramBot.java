package org.teamnescafe.jtbpdemo.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.teamnescafe.jtbpdemo.command.CommandContainer;
import org.teamnescafe.jtbpdemo.service.HomeworkService;
import org.teamnescafe.jtbpdemo.service.SendBotMessageServiceImpl;
import org.teamnescafe.jtbpdemo.service.StudentService;
import org.teamnescafe.jtbpdemo.service.TelegramUserService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.teamnescafe.jtbpdemo.command.CommandName.NO;

@Component
public class JavaTelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    public JavaTelegramBot(TelegramUserService telegramUserService, HomeworkService homeworkService, StudentService studentService) {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService, homeworkService, studentService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
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