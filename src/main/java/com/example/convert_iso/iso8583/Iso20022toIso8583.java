package com.example.convert_iso.iso8583;

import com.example.convert_iso.map.JsonPathUtil;
import com.example.convert_iso.map.ResponseCodeMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Iso20022toIso8583 {

    private static Object getRequiredValue(JsonPathUtil util, String path) {
        Object value = util.getValueByJsonPath(path);
        if (value == null || value == "") {
            throw new IllegalArgumentException("Missing required data at JSON Path: " + path);
        }
        return value;
    }

    public static String Iso20022toIso8583CA04(Map<String, Object> iso20022) throws JsonProcessingException {
        Map<String, Object> root = new HashMap<>();
        Map<String, Object> listField = new HashMap<>();
        root.put("mti", "0200");
        root.put("listField", listField);
        JsonPathUtil jsonPathUtil = new JsonPathUtil(iso20022);
        try {
            listField.put("100", getRequiredValue(jsonPathUtil, "Document.RtrAcct.RptOrErr.AcctRpt[0].AcctOrErr.Acct.Svcr.FinInstnId.ClrSysMmbId.MmbId"));
            listField.put("7", getRequiredValue(jsonPathUtil, "AppHdr.CreDt"));
            listField.put("2", getRequiredValue(jsonPathUtil, "Document.RtrAcct.RptOrErr.AcctRpt[0].AcctId.Othr.Id"));
            listField.put("32", "SGTTVNVX");
            listField.put("34", getRequiredValue(jsonPathUtil, "Document.RtrAcct.MsgHdr.OrgnlBizQry.MsgId"));
            listField.put("49", "VND");
            listField.put("61", "ACCOUNT");
            listField.put("103", "888888888");
            listField.put("105", "Auto_002_VIDPVNV5");

            listField.put("106", "21454657667878");
            listField.put("107", "CITIZEN_IDENTIFICATION_CARD");
//            listField.put("62", getRequiredValue(jsonPathUtil, "AppHdr.MsgDefIdr"));
//            listField.put("63", getRequiredValue(jsonPathUtil, "AppHdr.BizMsgIdr"));
//            listField.put("37", getRequiredValue(jsonPathUtil, "AppHdr.To.FIId.FinInstnId.ClrSysMmbId.MmbId"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    }

    public static String Iso20022toIso8583PA02(Map<String, Object> iso20022) throws JsonProcessingException {
        Map<String, Object> root = new HashMap<>();
        Map<String, Object> listField = new HashMap<>();
        root.put("mti", "0200");
        root.put("listField", listField);
        JsonPathUtil jsonPathUtil = new JsonPathUtil(iso20022);
        try {
            listField.put("100", getRequiredValue(jsonPathUtil, "AppHdr.To.FIId.FinInstnId.ClrSysMmbId.MmbId"));
            listField.put("7", getRequiredValue(jsonPathUtil, "AppHdr.CreDt"));
            listField.put("62", getRequiredValue(jsonPathUtil, "AppHdr.MsgDefIdr"));
            listField.put("63", getRequiredValue(jsonPathUtil, "AppHdr.BizMsgIdr"));
            listField.put("37", getRequiredValue(jsonPathUtil, "Document.FIToFIPmtStsRpt.TxInfAndSts[0].OrgnlEndToEndId"));
            listField.put("34", getRequiredValue(jsonPathUtil, "Document.FIToFIPmtStsRpt.OrgnlGrpInfAndSts[0].OrgnlMsgId"));
            listField.put("61", getRequiredValue(jsonPathUtil, "Document.FIToFIPmtStsRpt.TxInfAndSts[0].OrgnlEndToEndId"));
            String value39 = getRequiredValue(jsonPathUtil, "Document.FIToFIPmtStsRpt.TxInfAndSts[0].TxSts").toString();
            listField.put("38", getRequiredValue(jsonPathUtil, "Document.FIToFIPmtStsRpt.TxInfAndSts[0].AcctSvcrRef"));

            listField.put("39", ResponseCodeMap.toCode39(value39));
            listField.put("11", getRequiredValue(jsonPathUtil, "Document.FIToFIPmtStsRpt.TxInfAndSts[0].OrgnlInstrId"));
            listField.put("32", getRequiredValue(jsonPathUtil, "Document.FIToFIPmtStsRpt.TxInfAndSts[0].InstgAgt.FinInstnId.ClrSysMmbId.MmbId"));
//            listField.put("49", getRequiredValue(jsonPathUtil, "Document.FIToFIPmtStsRpt.TxInfAndSts[0].OrgnlTxRef.IntrBkSttlmAmt.Ccy"));
//            listField.put("5", getRequiredValue(jsonPathUtil, "Document.FIToFIPmtStsRpt.TxInfAndSts[0].OrgnlTxRef.IntrBkSttlmAmt.Value"));
            listField.put("49", "VND");
            listField.put("50", "704");
            listField.put("105", "NGUYEN VAN A");
            listField.put("5", "10000");
            listField.put("4", "10000");
            listField.put("128", "YOUR_SIGNATURE_HERE");
            listField.put("63", "");
            listField.put("15", "1027");
            listField.put("12", "101409");
            listField.put("13", "1027");
            listField.put("43", "NGUYEN VAN A chuyen tien");
            listField.put("103", "99010842");
            listField.put("102", "99010001");
            listField.put("120", "HOANG V WORIB");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    }

    public static String Iso20022toIso8583PA08(Map<String, Object> iso20022) throws JsonProcessingException {
        Map<String, Object> root = new HashMap<>();
        Map<String, Object> listField = new HashMap<>();
        root.put("mti", "0200");
        root.put("listField", listField);
        JsonPathUtil jsonPathUtil = new JsonPathUtil(iso20022);
        try {
            listField.put("100", getRequiredValue(jsonPathUtil, "AppHdr.To.FIId.FinInstnId.ClrSysMmbId.MmbId"));
            listField.put("5", String.valueOf(getRequiredValue(jsonPathUtil, "Document.FIToFICstmrCdtTrf.CdtTrfTxInf[0].IntrBkSttlmAmt.Value")));

            listField.put("7", getRequiredValue(jsonPathUtil, "AppHdr.CreDt"));

            listField.put("4", String.valueOf(getRequiredValue(jsonPathUtil, "Document.FIToFICstmrCdtTrf.CdtTrfTxInf[0].IntrBkSttlmAmt.Value")));
            listField.put("32", "SGTTVNVX");
            listField.put("37", getRequiredValue(jsonPathUtil, "Document.FIToFICstmrCdtTrf.CdtTrfTxInf[0].PmtId.InstrId"));
            listField.put("43", getRequiredValue(jsonPathUtil, "Document.FIToFICstmrCdtTrf.CdtTrfTxInf[0].InstrForNxtAgt[0].InstrInf"));

            listField.put("34", getRequiredValue(jsonPathUtil, "Document.FIToFICstmrCdtTrf.GrpHdr.MsgId"));
            listField.put("50", "VND");
            listField.put("49", "VND");
            listField.put("61", getRequiredValue(jsonPathUtil, "Document.FIToFICstmrCdtTrf.CdtTrfTxInf[0].PmtId.EndToEndId"));
            listField.put("62", getRequiredValue(jsonPathUtil, "AppHdr.MsgDefIdr"));
            listField.put("63", getRequiredValue(jsonPathUtil, "Document.FIToFICstmrCdtTrf.CdtTrfTxInf[0].PmtId.InstrId"));

            listField.put("102", getRequiredValue(jsonPathUtil, "Document.FIToFICstmrCdtTrf.CdtTrfTxInf[0].DbtrAcct.Id.Othr.Id"));
            listField.put("103", getRequiredValue(jsonPathUtil, "Document.FIToFICstmrCdtTrf.CdtTrfTxInf[0].CdtrAcct.Id.Othr.Id"));
            listField.put("105", getRequiredValue(jsonPathUtil, "Document.FIToFICstmrCdtTrf.CdtTrfTxInf[0].Dbtr.Nm"));
            listField.put("120", getRequiredValue(jsonPathUtil, "Document.FIToFICstmrCdtTrf.CdtTrfTxInf[0].Cdtr.Nm"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    }

    public static String Iso20022toIso8583CA03(Map<String, Object> iso20022) throws JsonProcessingException {
        Map<String, Object> root = new HashMap<>();
        Map<String, Object> listField = new HashMap<>();
        root.put("mti", "0200");
        root.put("listField", listField);
        JsonPathUtil jsonPathUtil = new JsonPathUtil(iso20022);
        try {
            listField.put("2", getRequiredValue(jsonPathUtil, "Document.GetAcct.AcctQryDef.AcctCrit.NewCrit.SchCrit[0].AcctId[0].EQ.Othr.Id"));

            listField.put("62", getRequiredValue(jsonPathUtil, "AppHdr.MsgDefIdr"));
            listField.put("7", getRequiredValue(jsonPathUtil, "AppHdr.CreDt"));
            listField.put("32", getRequiredValue(jsonPathUtil, "AppHdr.Fr.FIId.FinInstnId.ClrSysMmbId.MmbId"));
            listField.put("100", getRequiredValue(jsonPathUtil, "AppHdr.To.FIId.FinInstnId.ClrSysMmbId.MmbId"));
            listField.put("34", getRequiredValue(jsonPathUtil, "Document.GetAcct.MsgHdr.MsgId"));
            listField.put("102", getRequiredValue(jsonPathUtil, "Document.GetAcct.AcctQryDef.AcctCrit.NewCrit.SchCrit[0].AcctId[0].EQ.Othr.Id"));
            listField.put("103", getRequiredValue(jsonPathUtil, "Document.GetAcct.SplmtryData[0].Envlp.DbtrAcct.AcctId"));
            listField.put("105", getRequiredValue(jsonPathUtil, "Document.GetAcct.SplmtryData[0].Envlp.DbtrAcct.AcctNm"));
            listField.put("49", "VND");
            listField.put("106", getRequiredValue(jsonPathUtil, "Document.GetAcct.SplmtryData[0].Envlp.DbtrAcct.PrsnId.Id"));
            listField.put("107", getRequiredValue(jsonPathUtil, "Document.GetAcct.SplmtryData[0].Envlp.DbtrAcct.PrsnId.Tp.Prtry"));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    }
}