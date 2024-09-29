
package com.hospital.adapt.interceptor;

import com.hospital.adapt.utils.Ut;
import com.hospital.adapt.utils.Utl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ClientInterceptor implements HandlerInterceptor {
    Logger log = LoggerFactory.getLogger(ClientInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        String authorization = request.getHeader("Authorization");
        String auth = request.getParameter("Auth");
        if (Utl.auth(auth, authorization)) {
            return true;
        } else {
            returnJson(request, response);
            log.info("403 Forbidden, Auth failed!");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView mv) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {

    }

    private void returnJson(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            String result = Ut.buildStatusMsg("403", request.getParameter("act"), "Auth failed, pls check your user and passwd is right!", "", null);
            writer.print(result);
            writer.flush();
        } catch (IOException e) {
            log.error("ClientInterceptor returnJson exception:", e);
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }
}
