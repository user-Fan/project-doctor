package com.doctor.common;
import com.alibaba.fastjson.JSONObject;

public class ReturnUtil {
    /**
     * 返回值工具类
     * @param code  返回值 0 为成功 1为失败
     * @param msg   返回信息
     * @param data  返回参数
     * @return （返回值,返回信息,返回参数）
     */
    public static String toJSONString(Integer code, String msg, Object data) {
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("msg", msg);
        object.put("data", data);
        return object.toJSONString();
    }

}
