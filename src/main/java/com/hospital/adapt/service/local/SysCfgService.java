package com.hospital.adapt.service.local;

import com.alibaba.fastjson.JSONObject;
import com.hospital.adapt.model.local.DbCfgModel;

import java.util.Map;

public interface SysCfgService {
    JSONObject editDbCfg(Map<String, String[]> param, DbCfgModel dbCfg);
    JSONObject editGwCfg(Map<String, String[]> param);
    JSONObject queryDbCfg(Map<String, String[]> param);
    JSONObject queryGwCfg(Map<String, String[]> param);
    JSONObject queryVersion(Map<String, String[]> param);
}
