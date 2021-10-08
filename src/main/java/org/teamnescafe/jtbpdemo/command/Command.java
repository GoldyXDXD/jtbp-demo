package org.teamnescafe.jtbpdemo.command;

import net.fortuna.ical4j.data.ParserException;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public interface Command {
    void execute(Update update) throws IOException, ParserException;
}
