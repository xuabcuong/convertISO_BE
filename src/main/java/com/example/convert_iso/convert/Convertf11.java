package com.example.convert_iso.convert;

import com.example.convert_iso.map.BankCodeToSwiftMap;

import java.time.LocalDateTime;
import java.util.UUID;

public class Convertf11 {
    public static String convertf11(String mti, String F100, String F7) {

        String uuid = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 6)
                .toUpperCase();

        int year = LocalDateTime.now().getYear();
        String refurn = mti + BankCodeToSwiftMap.createBankCodeToSwiftMap().get(F100)
                + year + F7 + "000" + uuid;
        if (refurn.length() == 35) {
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
