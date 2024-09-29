package com.hospital.adapt.service.common;

import com.alibaba.fastjson.JSONObject;

public interface AdaptService {
    public JSONObject startAdapt(String adapt_type);
    public JSONObject stopAdapt(String adapt_type);
    public void setCfg(JSONObject cfg);
}
