package com.doctor.api.Vo;

import com.alibaba.fastjson.JSONObject;

public class Test001 {
    public static void main(String[] args) {
        String str = "{\"doctorId\":\"2\",\"endTime\":\"2019-03-07\",\"putReason\":\"12321321321\"}";
        JSONObject jsonObject=JSONObject.parseObject(str);
        OrderVo orderVo = JSONObject.toJavaObject(jsonObject,OrderVo.class);
        System.out.println(JSONObject.toJSONString(orderVo));

    }
}
