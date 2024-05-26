package com.kanshan.tea_classify.controller;

import com.google.gson.Gson;
import com.kanshan.tea_classify.entity.Record;
import com.kanshan.tea_classify.entity.Tea;
import com.kanshan.tea_classify.mapper.RecordMapper;
import com.kanshan.tea_classify.mapper.TeaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class recordController {
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private TeaMapper teaMapper;

    @PostMapping("/record")
    public String record(@RequestBody Map<String, String> paramMap){
        String openid = paramMap.get("openid");
        System.out.println("获取的openid为" + openid);

        List<Record> recordList = new ArrayList<Record>();
        recordList = recordMapper.findByOpenId(openid);

        Gson gson = new Gson();
        return gson.toJson(recordList);
    }

    @PostMapping("/recordToResult")
    public String recordToResult(@RequestBody Map<String, String> paramMap){
        String labelsStr = paramMap.get("labelstr");

        String[] labels = labelsStr.split(",");
        List<Tea> teas = new ArrayList<>();

//        通过label获取茶叶的相关信息
        for(String label : labels){
            teas.add(teaMapper.findByLabel(Integer.parseInt(label)));
        }

        Gson gson = new Gson();
        return gson.toJson(teas);
    }
}
