package com.example.convert_iso.map;

import java.util.HashMap;
import java.util.Map;

public class BankCodeToSwiftMap {

    public static String getBICbankcode(String bankCode) {
        String bicCode = createBankCodeToSwiftMap().get(bankCode);

        if (bicCode == null) {
            System.out.println(bankCode);
            throw new IllegalArgumentException("field ko hợp lệ");
        } else {
            return bicCode;
        }

    }

    public static Map<String, String> createBankCodeToSwiftMap() {
        Map<String, String> map = new HashMap<>();


        map.put("970499", "VBAAVNVX"); // Agribank
        map.put("970489", "ICBVVNVX"); // VietinBank
        map.put("970488", "BIDVVNVX"); // BIDV
        map.put("686868", "BFTVVNVX"); // Vietcombank

        // TMCP
        map.put("970416", "ASCBVNVX"); // ACB
        map.put("970403", "SGTTVNVX"); // Sacombank
        map.put("970422", "MSCBVNVX"); // MB Bank
        map.put("981957", "VPBKVNVX"); // VPBank
        map.put("888899", "VTCBVNVX"); // Techcombank
        map.put("970423", "TPBVVNVX"); // TPBank
        map.put("970443", "SHBAVNVX"); // SHB
        map.put("970437", "HDBCVNVX"); // HDBank
        map.put("970448", "ORCOVNVX"); // OCB
        map.put("970468", "SEAVVNVX"); // SeABank
        map.put("970459", "ABBKVNVX"); // ABBank
        map.put("970452", "KLBKVNVX"); // Kienlongbank
        map.put("970428", "NAMAVNVX"); // Nam A Bank
        map.put("970409", "NASCVNVX"); // Bac A Bank
        map.put("970438", "BVBVVNVX"); // BaoViet Bank
        map.put("818188", "NVBAVNVX"); // NCB
        map.put("970412", "WBVNVNVX"); // PVcomBank
        map.put("970430", "PGBLVNVX"); // PGBank
        map.put("970408", "GBNKVNVX"); // GPBank
        map.put("970414", "OCBKUS3M"); // OceanBank (nội địa, SWIFT hạn chế)
        map.put("157979", "SACLVNVX"); // SCB
        map.put("161087", "SBITVNVX"); // Saigonbank
        map.put("452999", "EBVIVNVX"); // Eximbank
        map.put("180906", "VNIBVNVX"); // VIB
        map.put("166888", "VNTTVNVX"); // VietABank
        map.put("970454", "VCBCVNVX"); // VietCapital Bank
        map.put("970433", "VNACVNVX"); // VietBank
        map.put("970449", "LVBKVNVX"); // LienVietPostBank

        // Ngân hàng nước ngoài / liên doanh
        map.put("970421", "VRBAVNVX"); // VRB
        map.put("970442", "HLBBVNVX"); // Hong Leong VN
        map.put("970424", "SHBKVNVX"); // Shinhan VN
        map.put("970439", "VIDPVNV5"); // VID Public
        map.put("970434", "IABBVNVX"); // Indovina
        map.put("970457", "HVBKVNVX"); // Woori VN
        map.put("970455", "IBKOVNVX"); // IBK
        map.put("422589", "CIBBVNVN"); // CIMB VN
        map.put("97045", "UOVBVNVX"); // UOB VN

        // Khác
        map.put("970446", null); // Co-op Bank (không SWIFT)
        map.put("970426", "MCOBVNVX"); // MSB (MaritimeBank)

        return map;
    }
}
