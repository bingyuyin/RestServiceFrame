package com.suiyu.comet.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by yinbing on 1/25/2016.
 */
public final class JsonUtils {
    private static final Gson gson = new Gson();

    private JsonUtils(){

    }
    public static String readJson(BufferedReader reader) {
        if (reader == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try {
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = reader.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString().trim();

    }

    public static JsonObject convertStringToJsonObject(String json){
        if(json == null){
            return null;
        }
        try {
            JsonParser jsonParser = new JsonParser();
            return (JsonObject) jsonParser.parse(json);
        }catch(Exception e){
            return null;
        }
    }
}
