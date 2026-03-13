package com.example.convert_iso.convert;

import com.example.convert_iso.map.BankCodeToSwiftMap;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Convertf11 {
    public static String convertf11(String mti, String F100, String F7) {

        String uuid = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 6)
                .toUpperCase();

        Instant instant = Instant.parse(F7);

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyyMMddHHmmssSSS")
                .withZone(ZoneOffset.UTC);

        String time = formatter.format(instant);

        String refurn = mti + F100 + time + uuid;

        if (refurn.length() == 35) {
            return refurn;
        } else {
            throw new IllegalArgumentException("field 11 invalid: " + refurn);
        }
    }

    public static String convertfMsgId(String MsgId, String F100, String F7) {

        String uuid = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 6)
                .toUpperCase();

        int year = LocalDateTime.now().getYear();
        String refurn = MsgId + BankCodeToSwiftMap.createBankCodeToSwiftMap().get(F100)
                + year + F7 + "000" + uuid;
        if (refurn.length() == 35) {
            System.out.println(refurn);
            return refurn;

        } else {
            throw new IllegalArgumentException(" field 11  " + refurn);
        }
    }

    public static String convertBizMsgIdr(String mti, String F100, String F7, String MTI) {

        String uuid = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 6)
                .toUpperCase();

        int year = LocalDateTime.now().getYear();

        String BICcode = BankCodeToSwiftMap.createBankCodeToSwiftMap().get(F100);
        if (BICcode == null) {
            throw new IllegalArgumentException(" field to map BICcode " + F100);

        }
        String refun = mti + BICcode
                + year + F7 + "000" + uuid;
        if (refun.length() == 35 && mti != null) {
            return refun;

        } else {
            throw new IllegalArgumentException(" field convert BizMsgIdr " + refun);
        }
    }
}
