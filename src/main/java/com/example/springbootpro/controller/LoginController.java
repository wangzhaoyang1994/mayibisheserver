package com.example.springbootpro.controller;

import com.example.springbootpro.entity.User;
import com.example.springbootpro.service.UserService;
import com.example.springbootpro.utils.JsonUtils;
import com.example.springbootpro.utils.StringUtils;
import com.example.springbootpro.utils.VerifyCodeUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/getVerify")
    public void getVerify(HttpServletResponse response,HttpServletRequest request) throws IOException {
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int w=132;
        int h=39;
        String verifyCode= VerifyCodeUtils.generateVerifyCode(4);
        String key="yzm";
        HttpSession session = request.getSession();
        session.setAttribute(key,verifyCode);
//        sessionUtils.set(request,key,verifyCode);
        VerifyCodeUtils.outputImage(w,h,response.getOutputStream(),verifyCode);
    }
    @PostMapping("/login")
    public JSONObject login(User user,HttpServletRequest request,@RequestParam("verify") String verify) {
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        boolean status = false;
        int error =0;//0 登陆成功 1 密码不正确 2 用户不存在 3 验证码不正确
        JSONObject result = new JSONObject();
        if (StringUtils.isNotEmpty("userName") && StringUtils.isNotEmpty("passWord")) {
            if (StringUtils.isNotEmpty("verCode")) {
                //验证码验证
                HttpSession session = request.getSession();
                String code = (String) session.getAttribute("yzm");
                List<User> userList = userService.selectUserByName(user.getUserName());
                if (verify.equalsIgnoreCase(code)) {
                    if (userList.size() > 0) {
                        if (user.getUserPassword().equals(userList.get(0).getUserPassword())) {
                            status = true;
                            result = JsonUtils.render(status, "登陆成功",0,userList.get(0));
                        } else {
                            result = JsonUtils.renderNew(status, "密码不正确",1);
                        }
                    } else {
                        result = JsonUtils.renderNew(status, "用户不存在",2);
                    }
                } else {
                    result = JsonUtils.renderNew(status, "输入的验证码不正确",3);
                }

            }

        }

        return result;
    }
}
