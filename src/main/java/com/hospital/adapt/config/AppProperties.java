package com.hospital.adapt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "my")
public class AppProperties {
    private String dsCfg = null;
    private String optCfg = null;
    private String localMapperPath = null;
    private String remoteMapperPath = null;
    private String thirdMapperPath = null;
    private String contxtPath = null;

    public String getContxtPath() {
        return contxtPath;
    }

    public void setContxtPath(String contxtPath) {
        this.contxtPath = contxtPath;
    }

    public String getDsCfg() {
        return dsCfg;
    }

    public void setDsCfg(String dsCfg) {
        this.dsCfg = dsCfg;
    }

    public String getOptCfg() {
        return optCfg;
    }

    public void setOptCfg(String optCfg) {
        this.optCfg = optCfg;
    }

    public String getLocalMapperPath() {
        return localMapperPath;
    }

    public void setLocalMapperPath(String localMapperPath) {
        this.localMapperPath = localMapperPath;
    }

    public String getRemoteMapperPath() {
        return remoteMapperPath;
    }

    public void setRemoteMapperPath(String remoteMapperPath) {
        this.remoteMapperPath = remoteMapperPath;
    }

    public String getThirdMapperPath() {
        return thirdMapperPath;
    }

    public void setThirdMapperPath(String thirdMapperPath) {
        this.thirdMapperPath = thirdMapperPath;
    }
}
