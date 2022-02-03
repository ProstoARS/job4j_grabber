package ru.job4j.grabber.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import static java.util.Map.entry;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final String TODAY = "сегодня,";
    private static final String YESTERDAY = "вчера,";

    private static final Map<String, String> MONTHS = Map.ofEntries(
            entry("янв", "1"),
            entry("фев", "2"),
            entry("мар", "3"),
            entry("апр", "4"),
            entry("май", "5"),
            entry("июн", "6"),
            entry("июл", "7"),
            entry("авг", "8"),
            entry("сен", "9"),
            entry("окт", "10"),
            entry("ноя", "11"),
            entry("дек", "12")
    );

    @Override
    public LocalDateTime parse(String parse) {
        LocalDateTime dateTime = null;
        String[] sub = parse.split(" ");
        if (sub.length == 2) {
            if (sub[0].equals(TODAY)) {
                dateTime = parseTime(sub[1]);
            } else if (sub[0].equals(YESTERDAY)) {
                dateTime = parseTime(sub[1]);
                dateTime = dateTime.minusDays(1);
            }
        }
        if (sub.length == 4) {
            int year = Integer.parseInt("20" + sub[2].replace(",", ""));
            int month = Integer.parseInt(MONTHS.get(sub[1]));
            int day = Integer.parseInt(sub[0]);
            String[] time = sub[3].split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            dateTime = LocalDateTime.of(year, month, day, hour, minute);
        }
        return dateTime;
    }

    private LocalDateTime parseTime(String s) {
        LocalDateTime dateTime;
        String[] subTime = s.split(":");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(Integer.parseInt(subTime[0]), Integer.parseInt(subTime[1]));
        dateTime = LocalDateTime.of(date, time);
        return dateTime;
    }
}