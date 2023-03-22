package com.kafarson.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    public static final String INPUT_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DEFAULT_OUTPUT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    public static String convertDateTime(String data, String outputPattern) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_TIME_PATTERN);
        LocalDateTime inputDate = LocalDateTime.parse(data, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputPattern);
        return outputFormatter.format(inputDate);
    }
}
