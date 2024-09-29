
package com.hospital.adapt.interceptor;

import com.hospital.adapt.model.local.LoBnUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        //	log.info("sid="+session.getId()+", uri="+request.getRequestURI());
        //	log.info("session user is null="+(null == session.getAttribute("user")));
        LoBnUser user = (LoBnUser) request.getSession().getAttribute("user");
        if (null == user) {
            log.info(request.getRemoteAddr() + " have not logon, goto login page!");
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView mv) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {

    }

}
