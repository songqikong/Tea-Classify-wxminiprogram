package com.kanshan.tea_classify.controller;

import com.google.gson.Gson;
import com.kanshan.tea_classify.Utils.Base64Util;
import com.kanshan.tea_classify.Utils.HttpHelper;

import com.kanshan.tea_classify.entity.Record;
import com.kanshan.tea_classify.entity.Tea;
import com.kanshan.tea_classify.mapper.RecordMapper;
import com.kanshan.tea_classify.mapper.TeaMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class identifyController {
    @Autowired
    private TeaMapper teaMapper;
    @Autowired
    private RecordMapper recordMapper;

    @PostMapping("/identify")
    public String identify(@RequestBody Map<String, String> paramMap){
        String photo_base64 = paramMap.get("photo");
//        将传送过来的base64转换为本地存储的图片
        String imgName = UUID.randomUUID().toString().concat(".jpg");
        Base64Util.strToImg(photo_base64,"D:\\teaimg\\" + imgName);
        String photoUrl = "http://127.0.0.1:8080/img/" + imgName;

        String openid = paramMap.get("openid");
        System.out.println("获取的openid为" + openid);
        String SECRET = "c656e210bde0cda4f833c5f507364d48";
//        传送给python时加上小程序的secret用作权限验证
        paramMap.put("secret", SECRET);

//        System.out.println(paramMap.entrySet());


        String url = "http://127.0.0.1:5000/identify";
        String requestResult = HttpHelper.doPost(url,paramMap);
        System.out.println("requestResult is _".replace("_", requestResult));

        if(requestResult.equals("-1")){
            return "-1";
        }else {

//        从pytorch获得三个最相似的label，将其分割分别获取相关信息
            String[] labels = requestResult.split(",");
            List<Tea> teas = new ArrayList<>();

//        通过label获取茶叶的相关信息
            for(String label : labels){
                teas.add(teaMapper.findByLabel(Integer.parseInt(label)));
            }

            if(!Objects.equals(openid, "notlogin")){
                Record record = new Record(0,requestResult, photoUrl, openid);

                System.out.println(record);
                try {
                    recordMapper.insert(record);
                }catch (Exception e){
                    System.out.println(e);
                }
            }

            List<Object> result = new ArrayList<>();
            result.add(teas);
            result.add(photoUrl);

            Gson gson = new Gson();
            return gson.toJson(result);
        }

    }
}
