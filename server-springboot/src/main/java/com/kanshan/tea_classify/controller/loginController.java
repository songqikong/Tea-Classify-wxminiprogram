package com.kanshan.tea_classify.controller;
import com.google.gson.Gson;
import com.kanshan.tea_classify.Utils.HttpHelper;
import com.kanshan.tea_classify.Utils.HttpRequestJsonEntity.OpenId;
import com.kanshan.tea_classify.entity.User;
import com.kanshan.tea_classify.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class loginController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String APPID = "wx81389333e069203f";
        String SECRET = "c656e210bde0cda4f833c5f507364d48";

        String JsCode = request.getParameter("code");

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + APPID +
                "&secret="
                + SECRET +
                "&js_code="
                + JsCode +
                "&grant_type=authorization_code";

        if(JsCode == null){
            return "code为空";
        }else {
//          apache的httpclient库，用来发送get请求
            String requestResult = HttpHelper.doGet(url);
            System.out.println(requestResult);

//          google的json解析库
            Gson gson = new Gson();
            OpenId openId = gson.fromJson(requestResult, OpenId.class);


            String openid = openId.getOpenid();
//          根据openid查找用户 如果不存在则新建用户，
            System.out.println(openid);
            User user =  userMapper.findById(openid);
            System.out.println(user);
            if(user == null){
                user = new User(openId.getOpenid(), "ava" ,"name", 0);
                userMapper.insert(user);

                List<Object> objectList = new ArrayList<>();
                objectList.add(openid);
                objectList.add(0);

                return gson.toJson(objectList);
            }

            List<Object> objectList = new ArrayList<>();
            objectList.add(openid);
            objectList.add(user.getGroup());
            return gson.toJson(objectList);
        }

    }
}
