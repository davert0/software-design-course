package com.github.davert0.date_time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateExample {

    // Потокобезопасный, компилируется один раз
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        String dateTimeStr = "2024-05-13 14:30:00";

        try {
            // Парсим без тайм-зоны
            LocalDateTime local = LocalDateTime.parse(dateTimeStr, FORMATTER);

            // Явно привязываем к нужному часовому поясу
            ZonedDateTime zoned = local.atZone(ZoneId.of("Europe/Moscow"));

            // Используем безопасные форматы
            System.out.println("ISO-8601: " + zoned);
            System.out.println("Epoch millis: " + zoned.toInstant().toEpochMilli());
        } catch (DateTimeParseException ex) {
            System.err.printf("Недопустимая дата-время: \"%s\"%n", dateTimeStr);
            ex.printStackTrace();
        }
    }
}
