
package com.hospital.adapt.controller;

import org.apache.catalina.manager.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class BaseController {
    Logger log = LoggerFactory.getLogger(BaseController.class);

    protected void outputString(HttpServletResponse response, String data) {
        try {
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=" + Constants.CHARSET);
            PrintWriter out = response.getWriter();
            out.println(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("BaseController outputString exception:", e);
        }
    }

}
