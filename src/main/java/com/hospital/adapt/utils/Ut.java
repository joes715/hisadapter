package com.hospital.adapt.utils;

import com.alibaba.fastjson.JSONObject;

public class Ut {
    public static String buildStatusMsg(String statusCode, String act, String msg, String att, Object dataModel) {
        JSONObject json = new JSONObject();
        json.put("statusCode", statusCode);
        json.put("act", act);
        json.put("msg", msg);
        json.put("att", att);
        json.put("data", dataModel);

        return json.toJSONString();
    }

    public static JSONObject getSucc(String act) {
        JSONObject json = new JSONObject();

        json.put("statusCode", "200");
        json.put("act", act);
        json.put("msg", "Success");
        json.put("att", "");

        return json;
    }

    public static JSONObject getFail(String act) {
        JSONObject json = new JSONObject();

        json.put("statusCode", "-1");
        json.put("act", act);
        json.put("msg", "Success");
        json.put("att", "");

        return json;
    }

}
