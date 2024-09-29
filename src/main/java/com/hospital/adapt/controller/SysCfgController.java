package com.hospital.adapt.controller;

import com.hospital.adapt.model.local.DbCfgModel;
import com.hospital.adapt.service.local.SysCfgService;
import com.hospital.adapt.utils.Str;
import com.hospital.adapt.utils.U;
import com.hospital.adapt.utils.Ut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class SysCfgController extends BaseController {
    @Resource
    private SysCfgService sysCfgService = null;

    @PostMapping(value = "/hospital/syscfg")
    public void edit(HttpServletRequest req, HttpServletResponse res, DbCfgModel dbCfg) {
        String act = U.get("act", req.getParameterMap());
        String result = Ut.getFail(act).toJSONString();

        if (Str.notNull(act)) {
            if (act.contentEquals("editDbCfg")) {
                result = sysCfgService.editDbCfg(req.getParameterMap(), dbCfg).toJSONString();
            } else if (act.contentEquals("editGwCfg")) {
                result = sysCfgService.editGwCfg(req.getParameterMap()).toJSONString();
            }
        }

        outputString(res, result);
    }

    @GetMapping(value = "/hospital/syscfg")
    public void query(HttpServletRequest req, HttpServletResponse res) {
        String act = U.get("act", req.getParameterMap());
        String result = Ut.getFail(act).toJSONString();

        if (Str.notNull(act)) {
            if (act.contentEquals("queryDbCfg")) {
                result = sysCfgService.queryDbCfg(req.getParameterMap()).toJSONString();
            } else if (act.contentEquals("queryGwCfg")) {
                result = sysCfgService.queryGwCfg(req.getParameterMap()).toJSONString();
            } else if (act.contentEquals("queryVersion")) {
                result = sysCfgService.queryVersion(req.getParameterMap()).toJSONString();
            }
        }

        outputString(res, result);
    }

}
