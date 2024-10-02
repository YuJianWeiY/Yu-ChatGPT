package com.yujianwei.yuchatgpt.controller;

import com.yujianwei.yuchatgpt.service.AIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AIController {
    @GetMapping("/chat")
    public String chatCompletions(String question){
        //定义变量接受service方法返回的智能回复结果，并且把结果返回到网页端
        //String result=aiService.chat("世界上最高的山峰是哪座且有多高？");
        String result=aiService.chat(question);
        //return "ChatGPT";
        return result;
    }

    @Resource
    private AIService aiService;

    @GetMapping("/img")
    public String img(String question) {
        //定义变量接受service方法返回的智能回复结果，并且把结果返回到网页端
        String result=aiService.img(question);
        return result;
    }
}
