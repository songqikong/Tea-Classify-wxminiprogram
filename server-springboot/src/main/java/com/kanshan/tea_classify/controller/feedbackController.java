package com.kanshan.tea_classify.controller;

import com.google.gson.Gson;
import com.kanshan.tea_classify.entity.FeedBack;
import com.kanshan.tea_classify.mapper.FeedBackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class feedbackController {
    @Autowired
    private FeedBackMapper feedBackMapper;

    @PostMapping("/feedback")
    public String feedback(@RequestBody Map<String, String> paramMap){
        String openid = paramMap.get("openid");
        String img_src = paramMap.get("img_src");
        String label = paramMap.get("label");

        feedBackMapper.insert(new FeedBack(openid, img_src, label, 0));

        return "success";
    }

    @PostMapping("/getfeedback")
    public String getfeedback(@RequestBody Map<String, String> paramMap){
        String openid = paramMap.get("openid");

        feedBackMapper.findByOpenId(openid);

        Gson gson = new Gson();
        return gson.toJson(feedBackMapper.findByOpenId(openid));
    }

    @PostMapping("/updatePermission")
    public String updatePermission(@RequestBody Map<String, String> paramMap){
        String id = paramMap.get("reviewid");
        String permission = paramMap.get("permission");
        System.out.println(id + "##" + permission);

        feedBackMapper.updatePermission(Integer.parseInt(id), Integer.parseInt(permission));

        return "successes";
    }
}
