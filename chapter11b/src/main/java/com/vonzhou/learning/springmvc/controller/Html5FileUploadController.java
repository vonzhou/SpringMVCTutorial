package com.vonzhou.learning.springmvc.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.vonzhou.learning.springmvc.domain.UploadedFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class Html5FileUploadController {

    private static final Log logger = LogFactory
            .getLog(Html5FileUploadController.class);

    @RequestMapping(value = "/html5")
    public String inputProduct() {
        return "Html5";
    }

    @RequestMapping(value = "/file_upload")
    public void saveFile(HttpServletRequest servletRequest,
                         @ModelAttribute UploadedFile uploadedFile,
                         BindingResult bindingResult, Model model) {

        MultipartFile multipartFile = uploadedFile.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();


        String imageDir = servletRequest.getSession().getServletContext().getRealPath("/image/");

        String imageFileName = imageDir + fileName;
        File imageFile = new File(imageFileName);
        File parent = imageFile.getParentFile();
        if (!parent.exists())
            parent.mkdirs();


        try {
            multipartFile.transferTo(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
