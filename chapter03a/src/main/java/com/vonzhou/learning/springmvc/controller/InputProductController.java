package com.vonzhou.learning.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InputProductController implements Controller {

//    private static final Log logger = LogFactory
//            .getLog(InputProductController.class);
    private static final Logger logger = Logger.getLogger(InputProductController.class);

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        //TODO 没有日志输出的问题:commons-logging是一个日志接口,log4j是具体的实现

        logger.info("InputProductController called");
        return new ModelAndView("/WEB-INF/jsp/ProductForm.jsp");
    }

}
