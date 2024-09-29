package com.hospital.adapt.controller;

import com.hospital.adapt.model.local.LoBnUser;
import com.hospital.adapt.service.local.LoUserService;
import com.hospital.adapt.utils.Str;
import com.hospital.adapt.utils.U;
import com.hospital.adapt.utils.Ut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoUserController extends BaseController {
    @Resource
    private LoUserService loUserService = null;

    @PutMapping(value = "/hospital/user")
    public void editPswd(HttpServletRequest req, HttpServletResponse res, LoBnUser bzUser) {
        String act = U.get("act", req.getParameterMap());
        String result = Ut.getFail(act).toJSONString();

        if (Str.notNull(act)) {
            if (act.contentEquals("updateAdminPswd")) {
                result = loUserService.modifyAdministratorPasswd(req.getParameterMap(), bzUser).toJSONString();
            }
        }

        outputString(res, result);
    }

}
