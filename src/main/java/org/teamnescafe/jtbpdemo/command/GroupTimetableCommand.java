package org.teamnescafe.jtbpdemo.command;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.filter.Filter;
import net.fortuna.ical4j.filter.predicate.PeriodRule;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.teamnescafe.jtbpdemo.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class GroupTimetableCommand implements Command {

    private static final String FILTER_MESSAGE = "Введите дату в формате xx.xx.xxxx";
    private static final String WRONG_FORMAT = "Неправильный формат даты. Вводи заново, чубрик.\nЕсли впадлу дальше искать, жми:\n/start";

    private final SendBotMessageService sendBotMessageService;
    private String timetable = "";

    @Autowired
    GroupTimetableCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    private static Collection getTimetable(int day, int month, int year) throws IOException, ParserException { //для получения нужного дня в расписании
        FileInputStream fin = new FileInputStream("calendar.ics"); //парсим расписание
        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(fin);
        java.util.Calendar filterDay = new GregorianCalendar(year, month - 1 , day);
        filterDay.set(java.util.Calendar.HOUR_OF_DAY, 0);
        filterDay.clear(java.util.Calendar.MINUTE);
        filterDay.clear(java.util.Calendar.SECOND);
        Period period = new Period(new DateTime(filterDay.getTime()), new Dur(1, 0, 0, 0));
        Filter filter = new Filter(new PeriodRule(period));
        return filter.filter(calendar.getComponents(Component.VEVENT)); //VEVENT используем для отображения только "мероприятий" календаря
    }

    @Override
    public void execute(Update update) throws IOException, ParserException {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), FILTER_MESSAGE);
        String message = update.getMessage().getText().trim();
        Pattern pattern = Pattern.compile("[0-9][0-9][.][0-9][0-9][.][0-9][0-9][0-9][0-9]");
        Matcher matcher = pattern.matcher(message);
        if (matcher.matches()) {
            String[] date = message.split(".");
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
            List timetable = new ArrayList(getTimetable(day, month, year));
            String[] timetableMessage = new String[timetable.size()];
            for (int i = 0; i < timetable.size(); i++) {
                VEvent vEvent = (VEvent) timetable.get(i);
                String description = vEvent.getDescription().toString();
                String title = vEvent.getSummary().toString();
                timetableMessage[i] = title + " : " + description;
            }
            String finalMessage = Stream.of(timetableMessage)
                    .collect(Collectors.joining("\n"));
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), finalMessage);
        } else {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), WRONG_FORMAT);
        }
    }
}