package com.example.convert_iso.convert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertTo8583 {
    public static String ConvertMsgIdToF37(String originalQueryId, String messageId) {

        if (originalQueryId == null || originalQueryId.length() < 20) {
            throw new IllegalArgumentException("OriginalQueryID invalid");
        }

        String dateStr = originalQueryId.substring(12, 20);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        String refun = dateStr.substring(3, 4) + String.valueOf(date.getDayOfYear()) + messageId;
        if (refun.length() != 11) {
            throw new IllegalArgumentException("field 37" + refun);
        } else {
            return refun;

        }
    }
}
