package com.fullstack.backend.util;

import java.util.Map;
import java.util.stream.Collectors;

public class ImportDataUtils {
    private static final String CONFIG_FILE  = "config";

    public static Map<String, String> getAllField(String prefix, String line) {
        return ResourceBundler.keySet(CONFIG_FILE).stream()
            .filter(key -> key.contains(prefix))
            .collect(Collectors.toMap(key -> key.split("\\.")[1], key -> getField(key, line)));
    }

    private static String getField(String key, String line) {
        String[] split = ResourceBundler.getProps(CONFIG_FILE, key).split(",");
        byte[] result = new byte[Integer.parseInt(split[1])];
        System.arraycopy(line.getBytes(), Integer.parseInt(split[0]), result, 0, Integer.parseInt(split[1]));
        return new String(result).trim();
    }
}
