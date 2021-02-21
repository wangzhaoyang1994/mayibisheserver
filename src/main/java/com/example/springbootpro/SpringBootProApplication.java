package com.example.springbootpro;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan({"com.example.springbootpro.mapper","com.example.springbootpro.mh.mapper"})//批量扫描所有mapper接口
public class SpringBootProApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootProApplication.class, args);
        String data = "{\"code\": 200, \"purchase_plan_code\": \"ZA20210310400\", \"type\": 1, \"result\": 0, \"errors\": {\"data\": [{\"id\": \"4461021295072534529\", \"name\": \"ZA20210310400\", \"msg\": \"BOM\\u7269\\u6599\\u7f16\\u53f7:09650103-09 \\u5b58\\u5728\\u66ff\\u4ee3\\u7ec4\\uff0c\\u4f46\\u672a\\u521b\\u5efa\\u591a\\u8f68!\"}, {\"id\": \"4461021295072534529\", \"name\": \"ZA20210310400\", \"msg\": \"BOM\\u7269\\u6599\\u7f16\\u53f7:09650275-86 \\u5b58\\u5728\\u66ff\\u4ee3\\u7ec4\\uff0c\\u4f46\\u672a\\u521b\\u5efa\\u591a\\u8f68!\"}, {\"id\": \"4461021295072534529\", \"name\": \"ZA20210310400\", \"msg\": \"BOM\\u7269\\u6599\\u7f16\\u53f7:09630027-83 \\u5b58\\u5728\\u66ff\\u4ee3\\u7ec4\\uff0c\\u4f46\\u672a\\u521b\\u5efa\\u591a\\u8f68!\"}, {\"id\": \"4461021295072534529\", \"name\": \"ZA20210310400\", \"msg\": \"BOM\\u7269\\u6599\\u7f16\\u53f7:6201112XNW01A \\u5b58\\u5728\\u66ff\\u4ee3\\u7ec4\\uff0c\\u4f46\\u672a\\u521b\\u5efa\\u591a\\u8f68!\"}]}}";
        JSONObject jsonObject = JSONObject.fromObject(data);
        int result = jsonObject.getInt("result");
        String purchaseCode = jsonObject.getString("purchase_plan_code");
        JSONObject jsonObject1 = jsonObject.getJSONObject("errors");

        //获取msg
        JSONArray jsonArray = jsonObject1.getJSONArray("data");
        String msg = "";
        for (int i = 0;i<jsonArray.size();i++){
            msg +=JSONObject.fromObject(jsonArray.optString(i)).optString("msg")+";";
        }
        System.out.println(result);
        System.out.println(purchaseCode);
        System.out.println(jsonObject1);
        System.out.println(jsonArray);
        System.out.println(msg);


    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值
        factory.setMaxFileSize(DataSize.parse("50MB")); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("50MB"));
        return factory.createMultipartConfig();
    }
}
