package com.hospital.adapt.service.local.impl;

import com.alibaba.fastjson.JSONObject;
import com.hospital.adapt.interceptor.ClientInterceptor;
import com.hospital.adapt.config.AppProperties;
import com.hospital.adapt.model.local.LoBnUser;
import com.hospital.adapt.service.local.LoUserService;
import com.hospital.adapt.utils.*;
import com.hospital.adapt.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Properties;

@Service
public class LoUserServiceImpl implements LoUserService {

    private AppProperties ap = null;
    Logger log = LoggerFactory.getLogger(ClientInterceptor.class);
    String optPath = null;

    @Resource
    public void setAppProperties(AppProperties ap) {
        this.ap = ap;
        optPath = ap.getContxtPath() + "/" + ap.getOptCfg();
    }

    @Override
    public LoBnUser userLoginCheck(LoBnUser bzUser) {
        LoBnUser u = null;

        if (null != bzUser
                && Str.notNull(bzUser.getUserAccount())
                && Str.notNull(bzUser.getUserPasswd())) {
            LoBnUser u2 = getAdminUser();
            if (null != u2) {
                if (u2.getUserAccount().contentEquals(bzUser.getUserAccount())
                        && u2.getUserPasswd().contentEquals(bzUser.getUserPasswd())) {

                    u = u2;
                    u.setUserAccount(CodecUtil.decode(u.getUserAccount()));
                }
            }
        }

        return u;
    }

    @Override
    public JSONObject modifyAdministratorPasswd(Map<String, String[]> param, LoBnUser bzUser) {
        String act = U.get("act", param);
        JSONObject json = Ut.getFail(act);

        try {
            if (null != bzUser && Str.notNull(bzUser.getUserAccount()) && bzUser.getUserAccount().contentEquals("admin")) {
                Properties prop = Utl.getProperties(optPath);
                if (null != prop) {
                    prop.put("userPasswd", CodecUtil.encode(bzUser.getUserPasswd()));
                    Utl.saveProperties(prop, optPath);
                    json = Ut.getSucc(act);
                }
            }
        } catch (Exception e) {
            log.error("LoUserServiceImpl updateAdminPswd exception:", e);
        }

        return json;
    }

    private LoBnUser getAdminUser() {
        LoBnUser u = new LoBnUser();
        Properties prop = Utl.getProperties(optPath);

        if (null != prop) {
            u.setUserNickname(prop.getProperty("userRole", "Administrator"));
            u.setUserAccount(prop.getProperty("userAccount", "admin"));
            u.setUserPasswd(prop.getProperty("userPasswd", "123456"));
            u.setUserRole(1);
        }

        return u;
    }
}
