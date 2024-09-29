package com.hospital.adapt.service.local.impl;

import com.hospital.adapt.model.local.LoBnUser;
import com.hospital.adapt.service.local.LoUserService;
import com.hospital.adapt.service.local.LogInOutService;
import com.hospital.adapt.utils.CodecUtil;
import com.hospital.adapt.utils.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class LogInOutServiceImpl implements LogInOutService {
    @Autowired
    private LoUserService loUserService = null;

    @Override
    public String userLogin(HttpServletRequest request, HttpServletResponse response, Model model, LoBnUser user) {
        if (Str.isNull(user.getUserAccount()) || Str.isNull(user.getUserPasswd())) {
            return "index";
        } else {
            user.setUserAccount(CodecUtil.encode(user.getUserAccount()));
            user.setUserPasswd(CodecUtil.encode(user.getUserPasswd()));
            LoBnUser bzUser = loUserService.userLoginCheck(user);
            if (null != bzUser) {
                request.getSession().setAttribute("user", bzUser);
                return "pages/main";
            } else {
                model.addAttribute("msg", "User or passwd error");
                return "index";
            }
        }
    }

    @Override
    public String userLogout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "index";
    }

}
