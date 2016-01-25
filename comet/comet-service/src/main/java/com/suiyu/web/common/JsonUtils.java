package com.suiyu.web.common;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by yinbing on 1/25/2016.
 */
public class JsonUtils {
    private static final Gson gson = new Gson();

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
}
