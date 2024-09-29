package com.hospital.adapt.service.local.impl;

import com.alibaba.fastjson.JSONObject;
import com.hospital.adapt.constant.Constant;
import com.hospital.adapt.config.AppProperties;
import com.hospital.adapt.service.local.AdaptCfgService;
import com.hospital.adapt.utils.U;
import com.hospital.adapt.utils.Ut;
import com.hospital.adapt.utils.Utl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Properties;

@Service
public class AdaptCfgServiceImpl implements AdaptCfgService {

    Logger log = LoggerFactory.getLogger(AdaptCfgServiceImpl.class);
    private AppProperties ap = null;
    String optPath = null;

    @Autowired
    public void setAppProperties(AppProperties ap) {
        this.ap = ap;
        optPath = ap.getContxtPath() + "/" + ap.getOptCfg();
    }

    @Override
    public JSONObject edit(Map<String, String[]> param) {
        String act = U.get("act", param);
        JSONObject json = Ut.getFail(act);

        Properties prop = Utl.getProperties(optPath);
        if (null != prop) {
            String enable_docking = U.get("enable_docking", param, Constant.ADAPT_OFF);
            String dock_type = U.get("dock_type", param, Constant.ADAPT_VIEW);
            String syn_patient_schedule = U.get("syn_patient_schedule", param, "10");
            String syn_all_schedule = U.get("syn_patient_schedule", param, "60");

            prop.put("enable_docking", enable_docking);
            prop.put("dock_type", dock_type);
            prop.put("syn_patient_schedule", syn_patient_schedule);
            prop.put("syn_all_schedule", syn_all_schedule);

            Utl.saveProperties(prop, optPath);

            json = Ut.getSucc(act);

            Constant.enableAdapt = enable_docking;
            Constant.adaptType = dock_type;
            Constant.synPatSchedule = Integer.parseInt(syn_patient_schedule);
            Constant.synAllSchedule = Integer.parseInt(syn_all_schedule);

            JSONObject rs = syncData(param);
            json.replace("msg", "Success!, " + rs.getString("msg"));
        }

        return json;
    }

    @Override
    public JSONObject queryAdaptCfg(Map<String, String[]> param) {
        String act = U.get("act", param);
        JSONObject json = Ut.getSucc(act);

        Properties prop = Utl.getProperties(optPath);
        if (null != prop) {
            json.put("enable_docking", prop.getProperty("enable_docking", Constant.ADAPT_OFF));
            json.put("dock_type", prop.getProperty("dock_type", Constant.ADAPT_VIEW));
            json.put("syn_patient_schedule", prop.getProperty("syn_patient_schedule", "10"));
            json.put("syn_all_schedule", prop.getProperty("syn_all_schedule", "60"));
        }

        return json;
    }

    @Override
    public JSONObject syncData(Map<String, String[]> param) {
        String act = U.get("act", param);
        JSONObject json = Ut.getSucc(act);

        if (null != Constant.adaptService) {
            Utl.setAdaptCfg();

            Constant.adaptService.stopAdapt(Constant.adaptType);

            if (Constant.enableAdapt.contentEquals(Constant.ADAPT_ON)) {
                json = Constant.adaptService.startAdapt(Constant.adaptType);
            } else {
                json.replace("msg", "Current status is off, service have not started.");
            }
        } else {
            json.replace("msg", "Service instance have not found, can not start service？");
        }

        return json;
    }
}
