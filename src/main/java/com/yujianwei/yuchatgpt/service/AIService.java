package com.yujianwei.yuchatgpt.service;

public interface AIService {
    //调用OpenAiApi接口：用户在界面输入一个问题，把问题传入接口的参数，这个接口返回这个问题的智能回复
    public String chat(String question);
    public String img(String question);
}
