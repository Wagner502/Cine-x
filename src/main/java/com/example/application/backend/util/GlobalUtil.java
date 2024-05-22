package com.example.application.backend.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GlobalUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String buildFormattedDateString(LocalDate dateTime) {
        return dateTime.format(FORMATTER);
    }
}
