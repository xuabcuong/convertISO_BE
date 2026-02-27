package com.example.convert_iso.map;

import java.util.HashMap;
import java.util.Map;

public class MtiMap {

    public static String getMessCode(String serviceCode) {
        Map<String, String> map = MapServiceCodeToMesscode();

        String messCode = map.get(serviceCode);

        if (messCode == null) {
            throw new IllegalArgumentException(
                    "mti không hợp lệ: " + serviceCode
            );
        }

        return messCode;
    }

    public static String getServiceCode(String serviceCode) {
        Map<String, String> map = MapMessCodeToServiceCode();

        String messCode = map.get(serviceCode);

        if (messCode == null) {
            throw new IllegalArgumentException(
                    "ServiceCode không hợp lệ: " + serviceCode
            );
        }
        return messCode;
    }

    public static Map<String, String> MapMessCodeToServiceCode() {
        Map<String, String> map = new HashMap<>();

        map.put("CA03", "0100");
        map.put("CA04", "0200");
        map.put("PA08", "0300");

        return map;
    }

    ;

    public static Map<String, String> MapServiceCodeToMesscode() {
        Map<String, String> map = new HashMap<>();

        map.put("0100", "CA03");
        map.put("0200", "CA04");
        map.put("0300", "PA08");

        return map;
    }

    ;

}
