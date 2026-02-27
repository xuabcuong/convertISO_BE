package com.example.convert_iso.convert;

public class ConvertProcessingCode {

    public static String detectType(String code) {
        if (code.length() == 6) {
            return "";
        } else {
            return "ACCOUNT";
        }
    }

}
