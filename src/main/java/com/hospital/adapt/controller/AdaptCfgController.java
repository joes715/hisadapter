package com.hospital.adapt.controller;

import com.hospital.adapt.service.local.AdaptCfgService;
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
public class AdaptCfgController extends BaseController {
    @Resource
    private AdaptCfgService adaptCfgService = null;

    @PostMapping(value = "/hospital/dockcfg")
    public void edit(HttpServletRequest req, HttpServletResponse res) {
        String act = U.get("act", req.getParameterMap());
        String result = Ut.getFail(act).toJSONString();

        if (Str.notNull(act)) {
            if (act.contentEquals("savedock")) {
                result = adaptCfgService.edit(req.getParameterMap()).toJSONString();
            }
        }

        outputString(res, result);
    }

    @GetMapping(value = "/hospital/dockcfg")
    public void query(HttpServletRequest req, HttpServletResponse res) {
        String act = U.get("act", req.getParameterMap());
        String result = Ut.getFail(act).toJSONString();

        if (Str.notNull(act)) {
            if (act.contentEquals("queryDockCfg")) {
                result = adaptCfgService.queryAdaptCfg(req.getParameterMap()).toJSONString();
            } else if (act.contentEquals("syncData")) {
                result = adaptCfgService.syncData(req.getParameterMap()).toJSONString();
            }
        }

        outputString(res, result);
    }

}
