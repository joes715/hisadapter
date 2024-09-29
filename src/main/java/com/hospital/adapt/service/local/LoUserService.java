package com.hospital.adapt.service.local;

import com.alibaba.fastjson.JSONObject;
import com.hospital.adapt.model.local.LoBnUser;

import java.util.Map;

public interface LoUserService {

    public LoBnUser userLoginCheck(LoBnUser bzUser);

    public JSONObject modifyAdministratorPasswd(Map<String, String[]> param, LoBnUser bzUser);

}
