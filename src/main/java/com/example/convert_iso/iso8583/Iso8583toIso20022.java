package com.example.convert_iso.iso8583;

import com.example.convert_iso.convert.Convertf11;

import com.example.convert_iso.map.ResponseCodeMap;
import tools.jackson.databind.ObjectMapper;


import java.math.BigDecimal;
import java.util.*;

public class Iso8583toIso20022 {
    public static String getvalueIso8583(Map<String, String> listfield, String field) {
        String value = listfield.get(field);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("field rỗng: " + field);
        }
        return value;

    }

    public static String convertCA03(String mti, Map<String, String> iso8583) {

        Map<String, Object> root = new HashMap<>();

        // ===== AppHdr =====
        Map<String, Object> appHdr = new HashMap<>();

        Map<String, Object> fr = Map.of(
                "FIId", Map.of(
                        "FinInstnId", Map.of(
                                "ClrSysMmbId", Map.of(
                                        "MmbId", "SGTTVNVX"
                                )
                        )
                )
        );

        Map<String, Object> to = Map.of(
                "FIId", Map.of(
                        "FinInstnId", Map.of(
                                "ClrSysMmbId", Map.of(
                                        "MmbId", (getvalueIso8583(iso8583, "100"))
                                )
                        )
                )
        );

        appHdr.put("Fr", fr);
        appHdr.put("To", to);

        appHdr.put("BizMsgIdr", Convertf11.convertf11(
                mti,
                "HVBKVNVX",
                getvalueIso8583(iso8583, "7")

        ));

//        appHdr.put("MsgDefIdr", getvalueIso8583(iso8583, "F32"));
        appHdr.put("BizSvc", "A2A");
        appHdr.put("MsgDefIdr", getvalueIso8583(iso8583, "62"));
        appHdr.put("CreDt", getvalueIso8583(iso8583, "7"));

        root.put("AppHdr", appHdr);

        // ===== MsgHdr =====
        Map<String, Object> msgHdr = new HashMap<>();
        msgHdr.put("MsgId", getvalueIso8583(iso8583, "34"));
        msgHdr.put("CreDtTm", getvalueIso8583(iso8583, "7"));

        msgHdr.put("ReqTp", Map.of(
                "Prtry", Map.of(
                        "Issr", getvalueIso8583(iso8583, "100")
                )
        ));

        // ===== Search Criteria =====
        Map<String, Object> schCrit = new HashMap<>();

        schCrit.put("AcctId", List.of(
                Map.of(
                        "EQ", Map.of(
                                "Othr", Map.of(
                                        "Id", getvalueIso8583(iso8583, "2"),
                                        "Issr", getvalueIso8583(iso8583, "100")
                                )
                        )
                )
        ));

        schCrit.put("Tp", List.of(
                Map.of("Prtry", "ACCOUNT")
        ));

        schCrit.put("AcctSvcr", Map.of(
                "FinInstnId", Map.of(
                        "ClrSysMmbId", Map.of(
                                "MmbId", getvalueIso8583(iso8583, "100")
                        )
                )
        ));

        schCrit.put("Ccy", getvalueIso8583(iso8583, "100"));

        Map<String, Object> acctQryDef = Map.of(
                "AcctCrit", Map.of(
                        "NewCrit", Map.of(
                                "SchCrit", List.of(schCrit)
                        )
                )
        );

        // ===== SplmtryData =====
        Map<String, Object> splmtryData = Map.of(
                "Envlp", Map.of(
                        "DbtrAcct", Map.of(
                                "AcctId", getvalueIso8583(iso8583, "103"),
                                "AcctNm", getvalueIso8583(iso8583, "105"),
                                "PrsnId", Map.of(
                                        "Id", getvalueIso8583(iso8583, "106"),
                                        "Tp", Map.of(
                                                "Prtry", getvalueIso8583(iso8583, "107")
                                        )
                                )
                        )
                )
        );

        Map<String, Object> getAcct = new HashMap<>();
        getAcct.put("MsgHdr", msgHdr);
        getAcct.put("AcctQryDef", acctQryDef);
        getAcct.put("SplmtryData", List.of(splmtryData));

        root.put("Document", Map.of("GetAcct", getAcct));

        // ===== Convert Map -> JSON =====
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        } catch (Exception e) {
            throw new RuntimeException("Convert JSON error", e);
        }
    }

    public static String convertPA08(String mti, Map<String, String> iso8583) {

        Map<String, Object> root = new HashMap<>();

        // ===== AppHdr =====
        Map<String, Object> appHdr = new HashMap<>();

        Map<String, Object> fr = Map.of(
                "FIId", Map.of(
                        "FinInstnId", Map.of(
                                "ClrSysMmbId", Map.of(
                                        "MmbId", "SGTTVNVX"
                                )
                        )
                )
        );

        Map<String, Object> to = Map.of(
                "FIId", Map.of(
                        "FinInstnId", Map.of(
                                "ClrSysMmbId", Map.of(
                                        "MmbId", (getvalueIso8583(iso8583, "100"))
                                )
                        )
                )
        );

        appHdr.put("Fr", fr);
        appHdr.put("To", to);

        appHdr.put("BizMsgIdr", getvalueIso8583(iso8583, "37"));

        appHdr.put("MsgDefIdr", getvalueIso8583(iso8583, "62"));
        appHdr.put("BizSvc", "A2A");
        appHdr.put("CreDt", getvalueIso8583(iso8583, "7"));

        root.put("AppHdr", appHdr);

        // -------- Group Header --------
        Map<String, Object> grpHdr = new LinkedHashMap<>();
        grpHdr.put("MsgId", getvalueIso8583(iso8583, "34"));
        grpHdr.put("CreDtTm", getvalueIso8583(iso8583, "7"));
        grpHdr.put("NbOfTxs", "1");

        grpHdr.put("TtlIntrBkSttlmAmt", Map.of(
                "Ccy", getvalueIso8583(iso8583, "49"),
                "Value", new BigDecimal(getvalueIso8583(iso8583, "5"))
        ));

        grpHdr.put("IntrBkSttlmDt", getvalueIso8583(iso8583, "7"));
        grpHdr.put("SttlmInf", Map.of("SttlmMtd", "CLRG"));

        // -------- Transaction --------
        Map<String, Object> tx = new LinkedHashMap<>();

        tx.put("PmtId", Map.of(
                "InstrId", getvalueIso8583(iso8583, "63"),
                "EndToEndId", getvalueIso8583(iso8583, "61"),
                "TxId", getvalueIso8583(iso8583, "63")
        ));

        tx.put("PmtTpInf", Map.of(
                "ClrChanl", "RTNS",
                "SvcLvl", List.of(Map.of("Prtry", "0100")),
                "LclInstrm", Map.of("Prtry", "BPDC"),
                "CtgyPurp", Map.of("Prtry", "0100")
        ));

        tx.put("IntrBkSttlmAmt", Map.of(
                "Ccy", getvalueIso8583(iso8583, "49"),
                "Value", new BigDecimal(getvalueIso8583(iso8583, "4"))
        ));

        tx.put("ChrgBr", "SLEV");

        tx.put("InstgAgt", Map.of(
                "FinInstnId", Map.of(
                        "ClrSysMmbId", Map.of("MmbId", (getvalueIso8583(iso8583, "32")))
                )
        ));

        tx.put("InstdAgt", Map.of(
                "FinInstnId", Map.of(
                        "ClrSysMmbId", Map.of("MmbId", getvalueIso8583(iso8583, "100"))
                )
        ));

        tx.put("Dbtr", Map.of(
                "Nm", "NGUYEN VAN A",
                "PstlAdr", Map.of("AdrLine", "N/A")
        ));

        tx.put("DbtrAcct", Map.of(
                "Id", Map.of(
                        "Othr", Map.of("Id", getvalueIso8583(iso8583, "103"))
                )
        ));

        tx.put("DbtrAgt", Map.of(
                "FinInstnId", Map.of(
                        "ClrSysMmbId", Map.of("MmbId", getvalueIso8583(iso8583, "32"))
                )
        ));
        tx.put("CdtrAgt", Map.of(
                "FinInstnId", Map.of(
                        "ClrSysMmbId", Map.of("MmbId", getvalueIso8583(iso8583, "100"))
                )
        ));


        tx.put("Cdtr", Map.of(
                "Nm", getvalueIso8583(iso8583, "120"),
                "PstlAdr", Map.of("AdrLine", "N/A")
        ));

        tx.put("CdtrAcct", Map.of(
                "Id", Map.of(
                        "Othr", Map.of("Id", getvalueIso8583(iso8583, "103"))
                ),
                "Tp", Map.of("Prtry", "0100")
        ));

        tx.put("InstrForNxtAgt", List.of(
                Map.of("InstrInf", getvalueIso8583(iso8583, "43"))
        ));

        tx.put("RmtInf", Map.of(
                "Ustrd", "Payment for invoice 123"//??
        ));

        // -------- FIToFICstmrCdtTrf --------
        Map<String, Object> fi = new LinkedHashMap<>();
        fi.put("GrpHdr", grpHdr);
        fi.put("CdtTrfTxInf", List.of(tx));

        Map<String, Object> document = new LinkedHashMap<>();
        document.put("FIToFICstmrCdtTrf", fi);

        root.put("Document", document);

        System.out.println(root);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(root);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertCA04(String mti, Map<String, String> iso8583) {

        Map<String, Object> root = new HashMap<>();
        // AppHdr
        Map<String, Object> appHdr = new HashMap<>();

        Map<String, Object> fr = Map.of(
                "FIId", Map.of(
                        "FinInstnId", Map.of(
                                "ClrSysMmbId", Map.of(
                                        "MmbId", "VPSSVNVX")
                        )
                )
        );

        Map<String, Object> to = Map.of(
                "FIId", Map.of(
                        "FinInstnId", Map.of(
                                "ClrSysMmbId", Map.of(
                                        "MmbId", "SGTTVNVX")
                        )
                )
        );

        appHdr.put("Fr", fr);
        appHdr.put("To", to);
        appHdr.put("BizMsgIdr", Convertf11.convertf11(
                mti,
                "HVBKVNVX",
                getvalueIso8583(iso8583, "7")

        ));
        appHdr.put("MsgDefIdr", "camt.004.001.10");
        appHdr.put("BizSvc", "A2A");
        appHdr.put("CreDt", getvalueIso8583(iso8583, "7"));
        root.put("AppHdr", appHdr);

        /* ---------------- MsgHdr ---------------- */

        Map<String, Object> msgHdr = new LinkedHashMap<>();
        msgHdr.put("MsgId", Convertf11.convertf11(
                "CA04",
                "VPSSVNVX",
                getvalueIso8583(iso8583, "7")

        ));//
        msgHdr.put("CreDtTm", getvalueIso8583(iso8583, "7"));

        msgHdr.put("OrgnlBizQry", Map.of(
                "MsgId", getvalueIso8583(iso8583, "34"),
                "CreDtTm", getvalueIso8583(iso8583, "7")
        ));

        /* ---------------- Owner Info ---------------- */

        Map<String, Object> owner = new LinkedHashMap<>();

        owner.put("Nm", getvalueIso8583(iso8583, "105"));
        owner.put("PstlAdr", Map.of(
                "Ctry", "VN",
                "AdrLine", "N/458 TIMES CITY,P. VINH TUY,TP. HA NOI"
        ));

        owner.put("Id", Map.of(
                "OrgId", Map.of(
                        "Othr", List.of(
                                Map.of(
                                        "Id", "0312345678",
                                        "SchmeNm", Map.of("Prtry", "CCCD"),
                                        "Issr", "GOV"
                                )
                        )
                ),
                "PrvtId", Map.of(
                        "Othr", List.of(
                                Map.of(
                                        "Id", "012345678901",
                                        "SchmeNm", Map.of("Prtry", "CCCD"),
                                        "Issr", ""
                                )
                        )
                )
        ));

        owner.put("CtryOfRes", "VN");

        owner.put("CtctDtls", Map.of(
                "Nm", getvalueIso8583(iso8583, "105"),
                "JobTitl", "DIRECTOR",
                "Rspnsblty", "ACCOUNT",
                "PhneNb", "02812345678",
                "MobNb", "0909123456",
                "EmailAdr", "a.nguyen@email.com"
        ));

        /* ---------------- Service Bank ---------------- */

        Map<String, Object> svcr = Map.of(
                "FinInstnId", Map.of(
                        "ClrSysMmbId", Map.of(
                                "MmbId", getvalueIso8583(iso8583, "100")
                        )
                )
        );

        /* ---------------- Currency Section ---------------- */

        Map<String, Object> ccy = new LinkedHashMap<>();

        ccy.put("CurMulLmt", Map.of(
                "CdtDbtInd", "CRDT"
        ));

        ccy.put("CcyCurMulLmt", Map.of(
                "Amt", Map.of(
                        "AmtWthtCcy", "10000000"
                )
        ));

        ccy.put("Prxy", Map.of(
                "Tp", Map.of("Prtry", "MASTER"),
                "Id", "2346866898"
        ));

        ccy.put("Ownr", owner);

        /* ---------------- Account ---------------- */

        Map<String, Object> acct = new LinkedHashMap<>();
        acct.put("Tp", Map.of("Prtry", "PAYMENT"));
        acct.put("Nm", "VND-1001-TKTT 19020868982669");
        acct.put("Ccy", ccy);
        acct.put("Svcr", svcr);


        /* ---------------- AccountOrError ---------------- */

        Map<String, Object> acctOrErr = new LinkedHashMap<>();

        acctOrErr.put("Acct", acct);

        acctOrErr.put("BizErr", List.of(
                Map.of(
                        "Err", Map.of("Prtry", "ERR-0030"),
                        "Desc", "Too many request"
                )
        ));

        /* ---------------- AcctRpt ---------------- */

        Map<String, Object> acctRpt = new LinkedHashMap<>();

        acctRpt.put("AcctId", Map.of(
                "Othr", Map.of("Id", "123456789")
        ));

        acctRpt.put("AcctOrErr", acctOrErr);

        /* ---------------- RptOrErr ---------------- */

        Map<String, Object> rptOrErr = new LinkedHashMap<>();

        rptOrErr.put("AcctRpt", List.of(acctRpt));

        rptOrErr.put("OprlErr", List.of(
                Map.of(
                        "Err", Map.of("Prtry", "SYSTEM_ERROR"),
                        "Desc", "Core banking timeout"
                )
        ));

        /* ---------------- RtrAcct ---------------- */

        Map<String, Object> rtrAcct = new LinkedHashMap<>();
        rtrAcct.put("MsgHdr", msgHdr);
        rtrAcct.put("RptOrErr", rptOrErr);

        Map<String, Object> document = new LinkedHashMap<>();
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


    public static String convertPA02(String mti, Map<String, String> iso8583) {

        Map<String, Object> root = new LinkedHashMap<>();

        Map<String, Object> appHdr = new HashMap<>();

        Map<String, Object> fr = Map.of(
                "FIId", Map.of(
                        "FinInstnId", Map.of(
                                "ClrSysMmbId", Map.of(
                                        "MmbId", getvalueIso8583(iso8583, "32")
                                )
                        )
                )
        );

        Map<String, Object> to = Map.of(
                "FIId", Map.of(
                        "FinInstnId", Map.of(
                                "ClrSysMmbId", Map.of(
                                        "MmbId", getvalueIso8583(iso8583, "100")
                                )
                        )
                )
        );

        appHdr.put("Fr", fr);
        appHdr.put("To", to);

        appHdr.put("BizMsgIdr", getvalueIso8583(iso8583, "37"));

        appHdr.put("BizSvc", "A2A");
        appHdr.put("MsgDefIdr", getvalueIso8583(iso8583, "62"));
        appHdr.put("CreDt", getvalueIso8583(iso8583, "7"));

        root.put("AppHdr", appHdr);

        /* -------- Group Header -------- */

        Map<String, Object> grpHdr = new LinkedHashMap<>();

        grpHdr.put("MsgId", "MSG0001");
        grpHdr.put("CreDtTm", getvalueIso8583(iso8583, "7"));

        grpHdr.put("InstgAgt", Map.of(
                "FinInstnId", Map.of(
                        "ClrSysMmbId", Map.of("MmbId", getvalueIso8583(iso8583, "32"))
                )
        ));

        grpHdr.put("InstdAgt", Map.of(
                "FinInstnId", Map.of(
                        "ClrSysMmbId", Map.of("MmbId", getvalueIso8583(iso8583, "100"))
                )
        ));

        /* -------- Original Group Info -------- */

        Map<String, Object> orgnlGrp = Map.of(
                "OrgnlMsgId", getvalueIso8583(iso8583, "34"),
                "OrgnlMsgNmId", getvalueIso8583(iso8583, "62"),
                "OrgnlCreDtTm", getvalueIso8583(iso8583, "7")
        );

        /* -------- Transaction Status -------- */

        Map<String, Object> tx = new LinkedHashMap<>();

        tx.put("OrgnlInstrId", getvalueIso8583(iso8583, "11"));
        tx.put("OrgnlEndToEndId", getvalueIso8583(iso8583, "61"));
        tx.put("OrgnlTxId", getvalueIso8583(iso8583, "11"));
        String value39 = getvalueIso8583(iso8583, "39");

        tx.put("TxSts", ResponseCodeMap.toTxSts(value39));

        tx.put("StsRsnInf", List.of(
                Map.of("AddtlInf", "Transaction successfully processed")
        ));

        tx.put("InstgAgt", Map.of(
                "FinInstnId", Map.of(
                        "ClrSysMmbId", Map.of("MmbId", getvalueIso8583(iso8583, "32"))
                )
        ));

        tx.put("InstdAgt", Map.of(
                "FinInstnId", Map.of(
                        "ClrSysMmbId", Map.of("MmbId", getvalueIso8583(iso8583, "32"))
                )
        ));

        tx.put("OrgnlTxRef", Map.of(
                "IntrBkSttlmDt", getvalueIso8583(iso8583, "7"),
                "IntrBkSttlmAmt", Map.of(
                        "Ccy", getvalueIso8583(iso8583, "49"),
                        "Value", getvalueIso8583(iso8583, "5")
                )
        ));

        tx.put("AcctSvcrRef", getvalueIso8583(iso8583, "38"));

        tx.put("TxSts", ResponseCodeMap.toTxSts(value39));



        /* -------- FIToFIPmtStsRpt -------- */

        Map<String, Object> fiToFi = new LinkedHashMap<>();

        fiToFi.put("GrpHdr", grpHdr);
        fiToFi.put("OrgnlGrpInfAndSts", List.of(orgnlGrp));
        fiToFi.put("TxInfAndSts", List.of(tx));

        Map<String, Object> document = new LinkedHashMap<>();
        document.put("FIToFIPmtStsRpt", fiToFi);

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


