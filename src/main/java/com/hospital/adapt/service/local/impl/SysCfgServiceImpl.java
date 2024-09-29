package com.hospital.adapt.service.local.impl;

import com.alibaba.fastjson.JSONObject;
import com.hospital.adapt.component.SpringUtils;
import com.hospital.adapt.constant.Constant;
import com.hospital.adapt.service.local.SysCfgService;
import com.hospital.adapt.utils.*;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.hospital.adapt.config.AppProperties;
import com.hospital.adapt.model.local.DbCfgModel;
import com.hospital.adapt.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Properties;

@Service
public class SysCfgServiceImpl implements SysCfgService {
    private AppProperties ap = null;
    String dsPath = null;
    String optPath = null;

    Logger log = LoggerFactory.getLogger(SysCfgServiceImpl.class);

    @Autowired
    public void setAppProperties(AppProperties ap) {
        this.ap = ap;
        dsPath = ap.getContxtPath() + "/" + ap.getDsCfg();
        optPath = ap.getContxtPath() + "/" + ap.getOptCfg();
    }

    @Override
    public JSONObject editDbCfg(Map<String, String[]> param, DbCfgModel dbCfg) {
        String act = U.get("act", param);
        JSONObject json = Ut.getFail(act);

        String ds_type = U.get("ds_type", param);
        try {
            Properties prop = null;
            if (ds_type.contentEquals("ds-ws")) {
                prop = Utl.getProperties(optPath);
            } else {
                prop = Utl.getProperties(dsPath);
            }

            if (null != prop) {
                if (ds_type.contentEquals("ds-local")) {
                    prop.put("hikari.local.username", (Str.isNull(dbCfg.getDb_user())) ? "" : CodecUtil.encode(dbCfg.getDb_user()));
                    prop.put("hikari.local.password", (Str.isNull(dbCfg.getDb_pswd())) ? "" : CodecUtil.encode(dbCfg.getDb_pswd()));
                    prop.put("hikari.local.driverClassName", (Str.isNull(dbCfg.getDb_driver())) ? "" : CodecUtil.encode(dbCfg.getDb_driver()));
                    prop.put("hikari.local.jdbcUrl", (Str.isNull(dbCfg.getDb_url())) ? "" : CodecUtil.encode(dbCfg.getDb_url()));
                    Utl.saveProperties(prop, dsPath);
                    json = Ut.getSucc(act);
                    resetDs("ds-local", dbCfg);
                } else if (ds_type.contentEquals("ds-his")) {
                    prop.put("hikari.remote.username", (Str.isNull(dbCfg.getDb_user())) ? "" : CodecUtil.encode(dbCfg.getDb_user()));
                    prop.put("hikari.remote.password", (Str.isNull(dbCfg.getDb_pswd())) ? "" : CodecUtil.encode(dbCfg.getDb_pswd()));
                    prop.put("hikari.remote.driverClassName", (Str.isNull(dbCfg.getDb_driver())) ? "" : CodecUtil.encode(dbCfg.getDb_driver()));
                    prop.put("hikari.remote.jdbcUrl", (Str.isNull(dbCfg.getDb_url())) ? "" : CodecUtil.encode(dbCfg.getDb_url()));
                    Utl.saveProperties(prop, dsPath);
                    json = Ut.getSucc(act);
                    resetDs("ds-his", dbCfg);
                } else if (ds_type.contentEquals("ds-th")) {
                    prop.put("hikari.third.username", (Str.isNull(dbCfg.getDb_user())) ? "" : CodecUtil.encode(dbCfg.getDb_user()));
                    prop.put("hikari.third.password", (Str.isNull(dbCfg.getDb_pswd())) ? "" : CodecUtil.encode(dbCfg.getDb_pswd()));
                    prop.put("hikari.third.driverClassName", (Str.isNull(dbCfg.getDb_driver())) ? "" : CodecUtil.encode(dbCfg.getDb_driver()));
                    prop.put("hikari.third.jdbcUrl", (Str.isNull(dbCfg.getDb_url())) ? "" : CodecUtil.encode(dbCfg.getDb_url()));
                    Utl.saveProperties(prop, dsPath);
                    json = Ut.getSucc(act);
                    resetDs("ds-th", dbCfg);
                } else if (ds_type.contentEquals("ds-ws")) {
                    prop.put("ws_url", (Str.isNull(dbCfg.getWs_url())) ? "" : CodecUtil.encode(dbCfg.getWs_url()));
                    prop.put("ws_opt1", (Str.isNull(dbCfg.getWs_opt1())) ? "" : CodecUtil.encode(dbCfg.getWs_opt1()));
                    prop.put("ws_opt2", (Str.isNull(dbCfg.getWs_opt2())) ? "" : CodecUtil.encode(dbCfg.getWs_opt2()));
                    Utl.saveProperties(prop, optPath);
                    json = Ut.getSucc(act);
                }
            }
        } catch (Exception e) {
            log.error("SysCfgServiceImpl editDbCfg exception:", e);
        }

        return json;
    }

    @Override
    public JSONObject queryDbCfg(Map<String, String[]> param) {
        String act = U.get("act", param);
        JSONObject json = Ut.getFail(act);

        String ds_type = U.get("ds_type", param);
        try {
            Properties prop = null;
            if (ds_type.contentEquals("ds-ws")) {
                prop = Utl.getProperties(optPath);
            } else {
                prop = Utl.getProperties(dsPath);
            }

            if (null != prop) {
                DbCfgModel dbCfg = new DbCfgModel();
                if (ds_type.contentEquals("ds-local")) {
                    dbCfg.setDb_user(CodecUtil.decode(prop.getProperty("hikari.local.username", "")));
                    dbCfg.setDb_pswd(CodecUtil.decode(prop.getProperty("hikari.local.password", "")));
                    dbCfg.setDb_driver(CodecUtil.decode(prop.getProperty("hikari.local.driverClassName", "")));
                    dbCfg.setDb_url(CodecUtil.decode(prop.getProperty("hikari.local.jdbcUrl", "")));
                    json = Ut.getSucc(act);
                    json.put("data", dbCfg);
                } else if (ds_type.contentEquals("ds-his")) {
                    dbCfg.setDb_user(CodecUtil.decode(prop.getProperty("hikari.remote.username", "")));
                    dbCfg.setDb_pswd(CodecUtil.decode(prop.getProperty("hikari.remote.password", "")));
                    dbCfg.setDb_driver(CodecUtil.decode(prop.getProperty("hikari.remote.driverClassName", "")));
                    dbCfg.setDb_url(CodecUtil.decode(prop.getProperty("hikari.remote.jdbcUrl", "")));
                    json = Ut.getSucc(act);
                    json.put("data", dbCfg);
                } else if (ds_type.contentEquals("ds-th")) {
                    dbCfg.setDb_user(CodecUtil.decode(prop.getProperty("hikari.third.username", "")));
                    dbCfg.setDb_pswd(CodecUtil.decode(prop.getProperty("hikari.third.password", "")));
                    dbCfg.setDb_driver(CodecUtil.decode(prop.getProperty("hikari.third.driverClassName", "")));
                    dbCfg.setDb_url(CodecUtil.decode(prop.getProperty("hikari.third.jdbcUrl", "")));
                    json = Ut.getSucc(act);
                    json.put("data", dbCfg);
                } else if (ds_type.contentEquals("ds-ws")) {
                    dbCfg.setWs_url(CodecUtil.decode(prop.getProperty("ws_url", "")));
                    dbCfg.setWs_opt1(CodecUtil.decode(prop.getProperty("ws_opt1", "")));
                    dbCfg.setWs_opt2(CodecUtil.decode(prop.getProperty("ws_opt2", "")));
                    json = Ut.getSucc(act);
                    json.put("data", dbCfg);
                }
            }
        } catch (Exception e) {
            log.error("SysCfgServiceImpl queryDbCfg exception:", e);
        }

        return json;
    }

    @Override
    public JSONObject editGwCfg(Map<String, String[]> param) {
        String act = U.get("act", param);
        JSONObject json = Ut.getFail(act);

        try {
            Properties prop = Utl.getProperties(optPath);

            if (null != prop) {
                String gw_host = U.get("gw_host", param, "127.0.0.1");
                String gw_port = U.get("gw_port", param, "9901");
                prop.put("gw_host", gw_host);
                prop.put("gw_port", gw_port);

                Utl.saveProperties(prop, optPath);
                json = Ut.getSucc(act);

                Constant.gwHost = gw_host;
                Constant.gwPort = gw_port;
            }
        } catch (Exception e) {
            log.error("SysCfgServiceImpl editGwCfg exception:", e);
        }

        return json;
    }

    @Override
    public JSONObject queryGwCfg(Map<String, String[]> param) {
        String act = U.get("act", param);
        JSONObject json = Ut.getFail(act);

        try {
            Properties prop = Utl.getProperties(optPath);

            if (null != prop) {
                String gw_host = prop.getProperty("gw_host", "127.0.0.1");
                String gw_port = prop.getProperty("gw_port", "9901");

                JSONObject data = new JSONObject();
                data.put("gw_host", gw_host);
                data.put("gw_port", gw_port);

                json = Ut.getSucc(act);
                json.put("data", data);
            }
        } catch (Exception e) {
            log.error("SysCfgServiceImpl queryGwCfg exception:", e);
        }

        return json;
    }

    @Override
    public JSONObject queryVersion(Map<String, String[]> param) {
        String act = U.get("act", param);
        JSONObject json = Ut.getSucc(act);

        try {
            Properties prop = Utl.getProperties(optPath);
            if (null != prop) {
                String version = prop.getProperty("version", "");
                json.replace("att", version);
            }
        } catch (Exception e) {
            log.error("SysCfgServiceImpl queryVersion exception:", e);
        }

        return json;
    }

    private void resetDs(String ds_type, DbCfgModel dbCfg) {
        try {
            ComboPooledDataSource ds = null;
            if (ds_type.contentEquals("ds-local")) {
                ds = SpringUtils.getBean("mLocalDataSource");
            } else if (ds_type.contentEquals("ds-his")) {
                ds = SpringUtils.getBean("mRemoteDataSource");
            } else if (ds_type.contentEquals("ds-th")) {
                ds = SpringUtils.getBean("mThirdDataSource");
            }

            if (null != ds) {
                ds.setUser(dbCfg.getDb_user());
                ds.setPassword(dbCfg.getDb_pswd());
                ds.setDriverClass(dbCfg.getDb_driver());
                ds.setJdbcUrl(dbCfg.getDb_url());
                ds.resetPoolManager();
                ds.hardReset();
                log.info("Reset " + ds_type + " datasources...");
            } else {
                log.info("Can not find datasource...");
            }
        } catch (Exception e) {
            log.error("SysCfgServiceImpl resetDs exception:", e);
        }
    }
}
