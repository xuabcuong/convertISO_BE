package com.example.convert_iso.map;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import java.util.Map;

public class JsonPathUtil {
    private final DocumentContext context;

    public JsonPathUtil(Map<String, Object> jsonMap) {
        Configuration conf = Configuration.defaultConfiguration()
                .addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);

        this.context = JsonPath.using(conf).parse(jsonMap);
    }

    public JsonPathUtil(String jsonString) {
        this.context = JsonPath.parse(jsonString);
    }

    public Object getValueByJsonPath(String path) {
        try {
            String fullPath = path.startsWith("$") ? path : "$." + path;
            return context.read(fullPath);
        } catch (Exception e) {
            return null;
        }
    }
}
