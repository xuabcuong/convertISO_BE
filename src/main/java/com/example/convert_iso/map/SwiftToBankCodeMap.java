package com.example.convert_iso.map;

import java.util.HashMap;
import java.util.Map;

public class SwiftToBankCodeMap {

    public static String getBankcode(String BICCode) {
        String bankcode = createSwiftToBankCodeMap().get(BICCode);


        if (bankcode == null) {

            throw new IllegalArgumentException("BIC ko hợp lệ");
        } else {
            return bankcode;
        }

    }

    public static Map<String, String> createSwiftToBankCodeMap() {
        Map<String, String> map = new HashMap<>();

        // Big 4
        map.put("VBAAVNVX", "970499"); // Agribank
        map.put("ICBVVNVX", "970489"); // VietinBank
        map.put("BIDVVNVX", "970488"); // BIDV
        map.put("BFTVVNVX", "686868"); // Vietcombank

        // TMCP
        map.put("ASCBVNVX", "970416"); // ACB
        map.put("SGTTVNVX", "970403"); // Sacombank
        map.put("MSCBVNVX", "970422"); // MB Bank
        map.put("VPBKVNVX", "981957"); // VPBank
        map.put("VTCBVNVX", "888899"); // Techcombank
        map.put("TPBVVNVX", "970423"); // TPBank
        map.put("SHBAVNVX", "970443"); // SHB
        map.put("HDBCVNVX", "970437"); // HDBank
        map.put("ORCOVNVX", "970448"); // OCB
        map.put("SEAVVNVX", "970468"); // SeABank
        map.put("ABBKVNVX", "970459"); // ABBank
        map.put("KLBKVNVX", "970452"); // Kienlongbank
        map.put("NAMAVNVX", "970428"); // Nam A Bank
        map.put("NASCVNVX", "970409"); // Bac A Bank
        map.put("BVBVVNVX", "970438"); // BaoViet Bank
        map.put("NVBAVNVX", "818188"); // NCB
        map.put("WBVNVNVX", "970412"); // PVcomBank
        map.put("PGBLVNVX", "970430"); // PGBank
        map.put("GBNKVNVX", "970408"); // GPBank
        map.put("OCBKUS3M", "970414"); // OceanBank
        map.put("SACLVNVX", "157979"); // SCB
        map.put("SBITVNVX", "161087"); // Saigonbank
        map.put("EBVIVNVX", "452999"); // Eximbank
        map.put("VNIBVNVX", "180906"); // VIB
        map.put("VNTTVNVX", "166888"); // VietABank
        map.put("VCBCVNVX", "970454"); // VietCapital Bank
        map.put("VNACVNVX", "970433"); // VietBank
        map.put("LVBKVNVX", "970449"); // LienVietPostBank

        // Ngân hàng nước ngoài / liên doanh
        map.put("VRBAVNVX", "970421"); // VRB
        map.put("HLBBVNVX", "970442"); // Hong Leong VN
        map.put("SHBKVNVX", "970424"); // Shinhan VN
        map.put("VIDPVNV5", "970439"); // VID Public
        map.put("IABBVNVX", "970434"); // Indovina
        map.put("HVBKVNVX", "970457"); // Woori VN
        map.put("IBKOVNVX", "970455"); // IBK
        map.put("CIBBVNVN", "422589"); // CIMB VN
        map.put("UOVBVNVX", "97045");  // UOB VN

        // Khác
        map.put("MCOBVNVX", "970426"); // MSB (MaritimeBank)
        // Co-op Bank không có SWIFT → bỏ qua

        return map;
    }
}
