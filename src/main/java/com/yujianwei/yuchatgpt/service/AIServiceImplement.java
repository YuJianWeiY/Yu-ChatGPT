package com.yujianwei.yuchatgpt.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class AIServiceImplement implements AIService{
    @Override
    public String chat(String question){
        //定义接口调用请求URL地址
        String url="https://api.openai-sb.com/v1/chat/completions" ;
        //定义请求的参数
        String json="{\n" +
                "    \"model\": \"gpt-3.5-turbo\",\n" +
                "    \"messages\": \n" +
                "          [{\"role\": \"user\", \"content\": \""+question+"\"}]\n" +
                "}";
        //创建一个请求体对象(body)
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, json);
        //创建一个请求对象
        Request request = new Request.Builder().post(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer sb-53e6294ab37cdb7c73c3a1146aa5665d0616427745dfc8d1")
                .url(url).build();
        //发送请求:创建了一个请求工具对象，调用执行request对象
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        //返回结果
        //String result=null;
        String content=null;
        //执行请求
        try {
            Response response = okHttpClient.newCall(request).execute();
            //打印返回的字符数据
            //System.out.println(response.body().string());
            String result=response.body().string();
            //解析json字符串
            JSONObject jsonObject = JSON.parseObject(result);
            JSONArray choices = jsonObject.getJSONArray("choices");
            if (choices != null) {
                Object o = choices.get(0);
                JSONObject jsonObject2 = JSON.parseObject(o.toString());
                JSONObject message = jsonObject2.getJSONObject("message");
                content = message.getString("content");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return result;
        return content;
    }

    @Override
    public String img(String question) {
        //定义接口调用请求URL地址
        String url="https://api.openai-hk.com/v1/images/generations" ;
        //定义请求的参数
        String json="{\n" +
                "    \"model\": \"dall-e-3\",\n" +
                "    \"prompt\": \""+question+"\",\n" +
                "    \"n\": 1,\n" +
                "    \"size\": \"1024x1024\"\n" +
                "  }";
        //创建一个请求体对象(body)
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, json);
        //创建一个请求对象
        Request request = new Request.Builder().post(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer hk-p1u1f2100004338933590d2ef82ee62e73967fd512283237")
                .url(url).build();
        //发送请求:创建了一个请求工具对象，调用执行request对象
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        String content=null;//返回结果
        //执行请求
        try {
            Response response = okHttpClient.newCall(request).execute();
            //打印返回的字符数据
            //System.out.println(response.body().string());
            String result=response.body().string();
            //解析json字符串
            JSONObject jsonObject = JSON.parseObject(result);
            JSONArray choices = jsonObject.getJSONArray("data");
            if (choices != null) {
                Object o = choices.get(0);
                JSONObject jsonObject2 = JSON.parseObject(o.toString());
                content = jsonObject2.getString("url");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
