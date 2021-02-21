package com.example.springbootpro.utils;

public class MessageUtils {
    public static String get(String msgKey) {
        if(msgKey.equals("success")){
            return "操作成功";
        }else{
            return "操作失败";
        }
    }
}
