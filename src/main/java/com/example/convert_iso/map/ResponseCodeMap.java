package com.example.convert_iso.map;

import java.util.Map;

public class ResponseCodeMap {

    // ISO8583 field 39 -> TxSts
    public static final Map<String, String> ISO8583_TO_TXSTS = Map.of(
            "00", "ACPT",
            "12", "DNID",
            "05", "RJCT",
            "68", "TOUT",
            "91", "EXPR"
    );

    // TxSts -> ISO8583 field 39
    public static final Map<String, String> TXSTS_TO_ISO8583 = Map.of(
            "ACPT", "00",
            "DNID", "12",
            "RJCT", "05",
            "TOUT", "68",
            "EXPR", "91"
    );

    public static String toTxSts(String code39) {
        return ISO8583_TO_TXSTS.get(code39);
    }

    public static String toCode39(String txSts) {
        return TXSTS_TO_ISO8583.get(txSts);
    }
}