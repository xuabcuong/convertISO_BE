package com.example.convert_iso.controller;

import com.example.convert_iso.util.JsonUtil;
import org.springframework.web.bind.annotation.*;
import com.example.convert_iso.iso8583.Iso20022toIso8583;
import com.example.convert_iso.iso8583.Iso8583toIso20022;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;


@CrossOrigin(origins = "https://convert-iso-fe.vercel.app")
@RestController
@RestControllerAdvice
@RequestMapping("/api")
public class ConvertController {


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidJson(HttpMessageNotReadableException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("JSON không hợp lệ: thiếu hoặc thừa {} / []");
    }


    @PostMapping("/8583to20022")
    public ResponseEntity<String> convert8583To20022(
            @RequestBody Map<String, Object> dataJson) {

        try {

            String mti = (String) dataJson.get("mti");

            Map<String, String> listField =
                    (Map<String, String>) dataJson.get("listField");

            String nameMasage = listField.get("62");

            if ("camt.004.001.10".equals(nameMasage)) {

                String result = Iso8583toIso20022.convertCA04(mti, listField);
                return ResponseEntity.ok(result);

            } else if ("camt.003.001.08".equals(nameMasage)) {

                String result = Iso8583toIso20022.convertCA03(mti, listField);
                return ResponseEntity.ok(result);

            } else if ("pacs.008.001.13".equals(nameMasage)) {

                String result = Iso8583toIso20022.convertPA08(mti, listField);
                return ResponseEntity.ok(result);

            } else if ("pacs.002.001.15".equals(nameMasage)) {

                String result = Iso8583toIso20022.convertPA02(mti, listField);
                return ResponseEntity.ok(result);

            } else {

                return ResponseEntity
                        .badRequest()
                        .body("Unsupported message type: " + nameMasage);
            }

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Convert failed: " + e.getMessage());
        }
    }

    @PostMapping(
            value = "/20022to8583",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<String> convert20022to8583(
            @RequestBody Map<String, Object> dataJson
    ) {

        try {


            String orgnlMsgNmId = JsonUtil.getMsgDefIdr(dataJson);

            if ("pacs.002.001.15".equals(orgnlMsgNmId)) {
                String result = Iso20022toIso8583.Iso20022toIso8583PA02(dataJson);
                return ResponseEntity.ok(result);

            } else if ("pacs.008.001.13".equals(orgnlMsgNmId)) {
                String result = Iso20022toIso8583.Iso20022toIso8583PA08(dataJson);
                return ResponseEntity.ok(result);

            } else if ("camt.003.001.08".equals(orgnlMsgNmId)) {
                String result = Iso20022toIso8583.Iso20022toIso8583CA03(dataJson);
                return ResponseEntity.ok(result);

            } else if ("camt.004.001.10".equals(orgnlMsgNmId)) {
                String result = Iso20022toIso8583.Iso20022toIso8583CA04(dataJson);
                return ResponseEntity.ok(result);

            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Unsupported OrgnlMsgNmId: " + orgnlMsgNmId);
            }

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }


}
