package com.hospital.adapt.controller;


import com.hospital.adapt.model.local.LoBnUser;
import com.hospital.adapt.service.local.LogInOutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/User/*")
public class LogInOutController extends BaseController {

    @Resource
    private LogInOutService logInOutService = null;

    @RequestMapping(value = "login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model, LoBnUser user) {
        return logInOutService.userLogin(request, response, model, user);
    }

    @RequestMapping(value = "logout")
    public String quitLogin(HttpServletRequest request, Model model) {
        return logInOutService.userLogout(request, model);
    }

}
