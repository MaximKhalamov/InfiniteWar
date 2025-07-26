package com.quazarx.infinitewar;

public class Parameters {
    // This parameter is for generating all the items, blocks, etc in JSON format to
    // process in scripts and generate placeholders
    public static boolean GENERATE_JSON = "true".equalsIgnoreCase(System.getenv("IW_GENERATE_JSON"));
    public static String JSON_PATH      = "../../definitions.json";
}
