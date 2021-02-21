package com.example.springbootpro.utils;

import com.example.springbootpro.entity.User;
import net.sf.json.JSONObject;

public class JsonUtils {
    public static JSONObject render(boolean status, String msg, int error, User user) {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("msg", msg);
        json.put("error",error);
        json.put("user",user);
        return json;
    }
    public static JSONObject renderNew(boolean status, String msg, int error) {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("msg", msg);
        json.put("error",error);
        return json;
    }
}
