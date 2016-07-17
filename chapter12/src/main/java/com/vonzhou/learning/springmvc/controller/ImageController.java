package com.vonzhou.learning.springmvc.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImageController {

    private static final Log logger = LogFactory.getLog(ImageController.class);

    @RequestMapping(value = "/image_get/{id}", method = RequestMethod.GET)
    public void getImage(@PathVariable String id,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestHeader String referer) {
        if (referer != null) {
            String imageDirectory = request.getSession().getServletContext().
                    getRealPath("/WEB-INF/image");
            File file = new File(imageDirectory,
                    id + ".jpg");
            if (file.exists()) {
                response.setContentType("image/jpg");
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                // if you're using Java 7, use try-with-resources
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (IOException ex) {
                    // do something here
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {

                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {

                        }
                    }
                }
            }
        }
    }
}
