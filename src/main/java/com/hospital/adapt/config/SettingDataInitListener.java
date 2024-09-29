package com.hospital.adapt.config;


import com.hospital.adapt.constant.Constant;
import com.hospital.adapt.service.common.AdaptService;
import com.hospital.adapt.utils.Utl;
import com.hospital.adapt.utils.CodecUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Properties;

@Component
public class SettingDataInitListener implements ApplicationListener<ContextRefreshedEvent> {
    Logger log = LoggerFactory.getLogger(SettingDataInitListener.class);
    private AppProperties ap = null;
    String optPath = null;

    @Autowired
    public void setAppProperties(AppProperties ap) {
        this.ap = ap;
        optPath = ap.getContxtPath() + "/" + ap.getOptCfg();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        WebApplicationContext webApplicationContext = (WebApplicationContext) contextRefreshedEvent.getApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        servletContext.setAttribute("TOKEN", Constant.TOKEN);

        log.info("Initing dock parameters...");
        initDockingParameters();
        log.info("Getting DockingServiceInstance and start docking service...");
        getDockingServiceInstance(webApplicationContext);
    }

    private void initDockingParameters() {
        Properties prop = Utl.getProperties(optPath);
        if (null != prop) {
            Constant.enableAdapt = prop.getProperty("enable_docking", Constant.ADAPT_OFF);
            Constant.adaptType = prop.getProperty("dock_type", Constant.ADAPT_VIEW);
            Constant.synPatSchedule = Integer.parseInt(prop.getProperty("syn_patient_schedule", "10"));
            Constant.synAllSchedule = Integer.parseInt(prop.getProperty("syn_all_schedule", "60"));
            Constant.wsUrl = CodecUtil.decode(prop.getProperty("ws_url", ""));
            Constant.wsOpt1 = CodecUtil.decode(prop.getProperty("ws_opt1", ""));
            Constant.wsOpt2 = CodecUtil.decode(prop.getProperty("ws_opt2", ""));
            log.info("Inited dock parameters finished");
        }
    }

    private void getDockingServiceInstance(WebApplicationContext webApplicationContext) {
        try {
            Object obj = webApplicationContext.getBean("adaptServiceImpl");
            if (null != obj) {
                Constant.adaptService = (AdaptService) obj;
            }

            if (null != Constant.adaptService) {
                Utl.setAdaptCfg();
                if (Constant.enableAdapt.contentEquals(Constant.ADAPT_ON)) {
                    Constant.adaptService.startAdapt(Constant.adaptType);
                } else {
                    log.warn("status is off, service not started");
                }
            } else {
                log.warn("Can not get Service instance, is it exist？");
            }
        } catch (Exception e) {
            log.error("Can not get Service instance, is it exist？", e.getMessage());
        }
    }

}
