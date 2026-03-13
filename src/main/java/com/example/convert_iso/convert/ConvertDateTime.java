package com.example.convert_iso.convert;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class ConvertDateTime {

    public static boolean isValidIso8601(String value) {
        try {
            Instant.parse(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String convertF7ToIso8601(String time) {

        if (time == null || time.length() != 10) {
            throw new IllegalArgumentException("Invalid F7 (MMDDhhmmss): " + time);
        }

        int year = LocalDateTime.now().getYear();
        String refun = String.format(
                "%04d-%s-%sT%s:%s:%s.000Z",
                year,
                time.substring(0, 2),  // MM
                time.substring(2, 4),  // DD
                time.substring(4, 6),  // hh
                time.substring(6, 8),  // mm
                time.substring(8, 10)  // ss
        );
        if (isValidIso8601(refun)) {
            return refun;
        } else {
            throw new IllegalArgumentException("Invalid F7 (MMDDhhmmss): " + time);

        }
    }

    public static String convertIso8601ToF7(String time) {
        if (time == null || !isValidIso8601(time)) {
            throw new IllegalArgumentException("Invalid ISO 8601 time: " + time);
        }
        return time.substring(5, 7)   // MM
                + time.substring(8, 10)  // DD
                + time.substring(11, 13) // hh
                + time.substring(14, 16) // mm
                + time.substring(17, 19);// ss
    }

    public static String convertsubstring(String time) {
        String isoDate = "2025-09-21T18:47:04.101Z";

        OffsetDateTime dateTime = OffsetDateTime.parse(isoDate);
        String result = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println(result);
        return result;
    }
}
