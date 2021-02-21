package com.example.springbootpro.controller;

import com.example.springbootpro.entity.CheckNum;
import com.example.springbootpro.entity.Test;
import com.example.springbootpro.utils.AjaxResult;
import com.example.springbootpro.utils.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/getTest")
    public AjaxResult getTest(){
        List<Test> testList = new ArrayList<>();
        List<Test> errorList = new ArrayList<>();
        for(int i =0 ;i<3;i++){
            Test test = new Test();
            test.setId(i);
            test.setTest("");
            test.setTest1("");
            test.setTest2("");
            test.setTest3(i);
            testList.add(test);
        }
        Test test1 = new Test();
        test1.setId(3);
        test1.setTest("");
        test1.setTest1("你好");
        test1.setTest2("你好1");
        test1.setTest3(10);
        Test test2 = new Test();
        test2.setId(4);
        test2.setTest("");
        test2.setTest1("");
        test2.setTest2("我很好");
        test2.setTest3(20);
        testList.add(test1);
        testList.add(test2);
        for (Test te:testList) {
            Test tt = new Test();
            Test tt1 = new Test();
            Test tt2 = new Test();
            Test tt3 = new Test();
            Test tt4 = new Test();
            if(StringUtils.isEmpty(te.getTest())){
                tt.setId(te.getId());
                tt.setTest(te.getTest());
                tt.setTest1(te.getTest1());
                tt.setTest2(te.getTest2());
                tt.setTest3(te.getTest3());
                tt.setFileReason("测试为空");
                errorList.add(tt);
            }
            if(StringUtils.isEmpty(te.getTest1())){
                tt1.setId(te.getId());
                tt1.setTest(te.getTest());
                tt1.setTest1(te.getTest1());
                tt1.setTest2(te.getTest2());
                tt1.setTest3(te.getTest3());
                tt1.setFileReason("测试为空1");
                errorList.add(tt1);
            }
            if(StringUtils.isEmpty(te.getTest2())){
                tt2.setId(te.getId());
                tt2.setTest(te.getTest());
                tt2.setTest1(te.getTest1());
                tt2.setTest2(te.getTest2());
                tt2.setTest3(te.getTest3());
                tt2.setFileReason("测试为空2");
                errorList.add(tt2);
            }
            if(StringUtils.isEmpty(te.getTest3().toString())){
                tt3.setId(te.getId());
                tt3.setTest(te.getTest());
                tt3.setTest1(te.getTest1());
                tt3.setTest2(te.getTest2());
                tt3.setTest3(te.getTest3());
                tt3.setFileReason("测试为空3");
                errorList.add(tt3);
            }
        }
        return AjaxResult.success(errorList);
    }
    @PostMapping("/dataCheck")
    public AjaxResult dataCheck(@RequestBody CheckNum checkNum){
        if(checkNum.getNumber().indexOf("%")!=-1){
            return AjaxResult.error(1,"使用比例不可填写百分数");
        }
        if(Double.valueOf(checkNum.getNumber())<0 || Double.valueOf(checkNum.getNumber())>100 || checkNum.getNumber().indexOf(".")!=-1){
            return AjaxResult.error(2,"使用比例为0-100的整数");
        }else{
            return AjaxResult.success();
        }
    }
}
