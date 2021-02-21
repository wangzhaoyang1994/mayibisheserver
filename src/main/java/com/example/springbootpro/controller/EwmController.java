package com.example.springbootpro.controller;

import com.example.springbootpro.utils.QRCodeUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/ewm")
public class EwmController {
    @Value("E:\\image")
    private String path;
    @GetMapping("/getEwm")
    public void getEwm() throws Exception {
        String text = "http://www.baidu.com";  //这里设置自定义网站url
        String logoPath = "E:\\erweima\\wangzhaoyang.JPG";
        String destPath = "E:\\erweima\\";
        QRCodeUtils.encode(text, logoPath, destPath, true);
    }
    @RequestMapping("/upload")
    public JSONObject uploadFile(@RequestParam("userImg") MultipartFile file, HttpServletRequest request) {
        //String path = request.getSession().getServletContext().getRealPath("/upload");
        System.out.println(path);
        JSONObject jsonResult = new JSONObject();
        if (!file.isEmpty()) {
            try {
                //图片命名
                File newFile = new File(path);
                if (!newFile.exists() && !newFile.isDirectory()) {
                    newFile.mkdir();
                }
                //获取原始文件名称(包含格式)
                String originalFileName = file.getOriginalFilename();
                //获取文件类型，以最后一个`.`为标识
                String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                System.out.println("文件类型：" + type);
                //获取文件名称（不包含格式）
                String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
                //设置文件新名称: 当前时间+文件名称（不包含格式）
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String date = sdf.format(d);
                String fileName = date + "." + type;
                System.out.println("新文件名称：" + fileName);
                //在指定路径下创建一个文件
                File targetFile = new File(path, fileName);
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(targetFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                String text = "http://8.131.87.154";  //这里设置自定义网站url
                String logoPath = "E:\\image\\"+fileName;
                String destPath = "E:\\image\\";
                String ewmImg = QRCodeUtils.encode(text, logoPath, destPath, true);
                //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
                String imgUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/image/" + ewmImg;//需要传绝对路径前端才可以访问
                jsonResult.put("path", imgUrl);
                System.out.println("路径：" + imgUrl);
            } catch (Exception e) {
                e.printStackTrace();
                jsonResult.put("code", 500);
                jsonResult.put("msg", "生成失败");
            }
        }
        jsonResult.put("code", 200);
        jsonResult.put("msg", "生成成功");
        return jsonResult;
    }
}
