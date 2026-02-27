package com.example.convert_iso.iso8583;

import com.example.convert_iso.convert.ConvertDateTime;
import com.example.convert_iso.convert.ConvertTo8583;
import com.example.convert_iso.map.JsonPathUtil;
import com.example.convert_iso.map.SwiftToBankCodeMap;
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

    public static String Iso20022toIso8583(Map<String, Object> iso20022) throws JsonProcessingException {
        Map<String, Object> root = new HashMap<>();
        Map<String, Object> listField = new HashMap<>();
        root.put("mti", "0200");
        root.put("listField", listField);
        JsonPathUtil jsonPathUtil = new JsonPathUtil(iso20022);
        try {

            listField.put("2", "");
            listField.put("37", String.valueOf(ConvertTo8583.ConvertMsgIdToF37(
                    (String) getRequiredValue(jsonPathUtil, "Document.RtrAcct.MsgHdr.OrgnlBizQry.MsgId"),
                    "0127144"
            )));
            listField.put("7", ConvertDateTime.convertIso8601ToF7((String) getRequiredValue(jsonPathUtil, "Document.RtrAcct.MsgHdr.CreDtTm")
            ));


            listField.put("103", getRequiredValue(jsonPathUtil, "Document.RtrAcct.RptOrErr.AcctRpt[0].AcctId.Othr.Id"));
            listField.put("120", getRequiredValue(jsonPathUtil, "Document.RtrAcct.RptOrErr.AcctRpt[0].AcctOrErr.Acct.Nm"));

            listField.put("100", 970432);

            String bankCode = SwiftToBankCodeMap.createSwiftToBankCodeMap().get(
                    getRequiredValue(jsonPathUtil, "AppHdr.Fr.FIId.FinInstnId.ClrSysMmbId.MmbId")
            );


            if (bankCode == null) throw new RuntimeException("Bank code not found for the given Swift ID");

            listField.put("32", Integer.valueOf(bankCode));

            listField.put("11", getRequiredValue(jsonPathUtil, "Document.RtrAcct.MsgHdr.MsgId"));
            listField.put("11", "0127144");

            listField.put("49", 704);
            listField.put("60", getRequiredValue(jsonPathUtil, "AppHdr.BizSvc"));

            listField.put("105", getRequiredValue(jsonPathUtil, "Document.RtrAcct.RptOrErr.AcctRpt[0].AcctOrErr.Acct.Ownr.Nm"));
            listField.put("106", getRequiredValue(jsonPathUtil, "Document.RtrAcct.RptOrErr.AcctRpt[0].AcctId.Othr.Id"));
            listField.put("107", getRequiredValue(jsonPathUtil, "Document.RtrAcct.RptOrErr.AcctRpt[0].AcctOrErr.Acct.Ownr.Id.PrvtId.Othr[0].SchmeNm.Prtry"));

        } catch (IllegalArgumentException e) {
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    }
}