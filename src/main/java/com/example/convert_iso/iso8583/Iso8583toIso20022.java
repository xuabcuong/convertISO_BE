package com.example.convert_iso.iso8583;

import com.example.convert_iso.convert.ConvertDateTime;
import com.example.convert_iso.convert.ConvertProcessingCode;
import com.example.convert_iso.convert.Convertf11;
import com.example.convert_iso.map.BankCodeToSwiftMap;
import com.example.convert_iso.map.MtiMap;
import tools.jackson.databind.ObjectMapper;

import java.util.*;

public class Iso8583toIso20022 {
    public static String getvalueIso8583(Map<String, String> listfield, String field) {
        String value = listfield.get(field);
        if (value == null || value == "") {
            throw new IllegalArgumentException("field rỗng :" + field);
        } else {
            return value;
        }

    }

    public static String convert(String mti, Map<String, String> iso8583) {

        Map<String, Object> root = new HashMap<>();

        Map<String, Object> appHdr = new HashMap<>();

        appHdr.put("Fr", Map.of(
                "FIId", Map.of(
                        "FinInstnId", Map.of(
                                "ClrSysMmbId", Map.of("MmbId", BankCodeToSwiftMap.getBICbankcode(getvalueIso8583(iso8583, "37")))

                        )
                )
        ));

        appHdr.put("To", Map.of(
                "FIId", Map.of(
                        "FinInstnId", Map.of(
                                "ClrSysMmbId", Map.of("MmbId", BankCodeToSwiftMap.getBICbankcode(getvalueIso8583(iso8583, "100")))
                        )
                )
        ));

        appHdr.put("BizMsgIdr", Convertf11.convertf11(
                MtiMap.getMessCode(mti),
                getvalueIso8583(iso8583, "100"),
                getvalueIso8583(iso8583, "7")

        ));
        appHdr.put("MsgDefIdr", "camt.004.001.10");
        appHdr.put("BizSvc", getvalueIso8583(iso8583, "60"));
        appHdr.put("CreDt", ConvertDateTime.convertF7ToIso8601(getvalueIso8583(iso8583, "7")));

        root.put("AppHdr", appHdr);

        /* ================= Document ================= */
        Map<String, Object> document = new HashMap<>();
        Map<String, Object> rtrAcct = new HashMap<>();

        /* ===== MsgHdr ===== */
        Map<String, Object> msgHdr = new HashMap<>();
        msgHdr.put("MsgId", Convertf11.convertBizMsgIdr(
                MtiMap.getMessCode(mti),
                getvalueIso8583(iso8583, "100"),
                getvalueIso8583(iso8583, "7"),
                mti

        ));
        msgHdr.put("CreDtTm", ConvertDateTime.convertF7ToIso8601(getvalueIso8583(iso8583, "7")));

        msgHdr.put("OrgnlBizQry", Map.of(
                "MsgId", Objects.requireNonNull(msgHdr.put("MsgId", Convertf11.convertBizMsgIdr(
                        MtiMap.getMessCode(mti),
                        getvalueIso8583(iso8583, "100"),
                        getvalueIso8583(iso8583, "7"),
                        "0200"
                )))
                ,
                "CreDtTm", ConvertDateTime.convertF7ToIso8601(getvalueIso8583(iso8583, "7"))
        ));

        rtrAcct.put("MsgHdr", msgHdr);

        /* ===== RptOrErr -> AcctRpt (ARRAY) ===== */
        List<Map<String, Object>> acctRptArr = new ArrayList<>();

        Map<String, Object> acctRpt = new HashMap<>();

        acctRpt.put("AcctId", Map.of(
                "Othr", Map.of("Id", getvalueIso8583(iso8583, "62"))
        ));

        Map<String, Object> acct = new HashMap<>();
        acct.put("Nm", "TKTT " + getvalueIso8583(iso8583, "62"));//!!
        acct.put("Tp", Map.of("Prtry", getvalueIso8583(iso8583, "61")));
        acct.put("Ccy", "VND");
        acct.put("Prxy", Map.of("Id", getvalueIso8583(iso8583, "62")));

        acct.put("Ownr", Map.of(
                "Nm", getvalueIso8583(iso8583, "105"),
                "PstlAdr", Map.of(
                        "Ctry", "VN",
                        "AdrLine", List.of("458 Minh Khai, Vinh Tuy, Ha Noi")
                ),
                "Id", Map.of(
                        "OrgId", Map.of(
                                "Othr", List.of(
                                        Map.of(
                                                "Id", "ID1",
                                                "SchmeNm", Map.of("Prtry", "BUSINESS_REGISTRATION")
                                        )
                                )
                        ),
                        "PrvtId", Map.of(
                                "Othr", List.of(
                                        Map.of(
                                                "Id", "ID",
                                                "SchmeNm", Map.of("Prtry", getvalueIso8583(iso8583, "107"))
                                        )
                                )
                        )
                )
        ));

        acct.put("Svcr", Map.of(
                "FinInstnId", Map.of(
                        "ClrSysMmbId", Map.of("MmbId", BankCodeToSwiftMap.getBICbankcode(getvalueIso8583(iso8583, "100")))
                )
        ));

        acctRpt.put("AcctOrErr", Map.of("Acct", acct));
        acctRptArr.add(acctRpt);

        rtrAcct.put("RptOrErr", Map.of("AcctRpt", acctRptArr));


        List<Map<String, Object>> splmtryData = new ArrayList<>();

        splmtryData.add(
                Map.of(
                        "Envlp", Map.of(
                                "FraudAMLCheckResult", Map.of(
                                        "AmlFraudStatus", "SUSPICIOUS",
                                        "Transaction", Map.of(
                                                "TransactionSchema", "100",
                                                "RiskScore", 100
                                        ),
                                        "ScreeningSenderResult", Map.of(
                                                "Simo", Map.of(
                                                        "FlagCode", "NO_HIT",
                                                        "FlagReasons", "1"
                                                ),
                                                "A05", Map.of(
                                                        "FlagCode", "NO_HIT",
                                                        "FlagReasons", List.of(
                                                                Map.of(
                                                                        "WarningType", "1",
                                                                        "FraudMethod", "1"
                                                                )
                                                        )
                                                ),
                                                "Mdp", Map.of(
                                                        "FlagCode", "HIT",
                                                        "FlagReasons", List.of("37", "26", "27")
                                                )
                                        ),
                                        "ScreeningRecipientResult", Map.of(
                                                "Simo", Map.of(
                                                        "FlagCode", "NO_HIT",
                                                        "FlagReasons", "1"
                                                ),
                                                "A05", Map.of(
                                                        "FlagCode", "NO_HIT",
                                                        "FlagReasons", List.of(
                                                                Map.of(
                                                                        "WarningType", "1",
                                                                        "FraudMethod", "1"
                                                                )
                                                        )
                                                ),
                                                "Mdp", Map.of(
                                                        "FlagCode", "HIT",
                                                        "FlagReasons", List.of("37", "26", "27")
                                                )
                                        )
                                )
                        )
                )
        );

        rtrAcct.put("SplmtryData", splmtryData);

        document.put("RtrAcct", rtrAcct);
        root.put("Document", document);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(root);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}