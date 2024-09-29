package com.hospital.adapt.utils;

import com.alibaba.fastjson.JSONObject;
import com.hospital.adapt.constant.Constant;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.*;
import java.util.*;

public class Utl {

    static Logger log = LoggerFactory.getLogger(Utl.class);

    public static boolean saveProperties(Properties prop, String rPath) {
        boolean result = false;

        try {
            FileOutputStream fos = new FileOutputStream(rPath);
            prop.store(fos, "save prop");
            fos.close();
            result = true;
        } catch (IOException e) {
            log.error("Utl saveProperties exception:", e);
            result = false;
        }

        return result;
    }

    public static Properties getProperties(String rPath) {
        Properties prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(rPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            prop.load(br);
            br.close();
        } catch (IOException e) {
            log.error("Utl getProperties exception:", e);
        }

        return prop;
    }

    public static boolean auth(String auth, String uid) {
        return ((null != auth)
                && (null != uid)
                && (auth.trim().length() > 0)
                && (uid.trim().length() > 0)
                && auth.contentEquals(getMD5Code(uid + Constant.TOKEN)));
    }

    public static String getMD5Code(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static void setAdaptCfg() {
        if (null != Constant.adaptService) {
            JSONObject dockingCfg = new JSONObject();
            dockingCfg.put("enable_docking", Constant.enableAdapt);
            dockingCfg.put("dock_type", Constant.adaptType);
            dockingCfg.put("syn_patient_schedule", Constant.synPatSchedule);
            dockingCfg.put("syn_all_schedule", Constant.synAllSchedule);
            dockingCfg.put("ws_url", Constant.wsUrl);
            dockingCfg.put("ws_opt1", Constant.wsOpt1);
            dockingCfg.put("ws_opt2", Constant.wsOpt2);
            Constant.adaptService.setCfg(dockingCfg);
        }
    }

    private static void main(String args[]) {

    }
}
