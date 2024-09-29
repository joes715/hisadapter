package com.hospital.adapt.service.local;

import com.hospital.adapt.model.local.LoBnUser;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogInOutService {
    public String userLogin(HttpServletRequest request, HttpServletResponse response, Model model, LoBnUser user);

    public String userLogout(HttpServletRequest request, Model model);
}
