package com.hospital.adapt.service.local;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface AdaptCfgService {

    JSONObject edit(Map<String, String[]> param);

    JSONObject queryAdaptCfg(Map<String, String[]> param);

    JSONObject syncData(Map<String, String[]> param);
}
