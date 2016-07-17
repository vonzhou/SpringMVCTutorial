package com.vonzhou.learning.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vonzhou.learning.springmvc.domain.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProductController implements HandlerExceptionResolver {

    private static final Log logger = LogFactory
            .getLog(ProductController.class);

    @RequestMapping(value = "/product_input")
    public String inputProduct(Model model) {
        model.addAttribute("product", new Product());
        return "ProductForm";
    }

    @RequestMapping(value = "/product_save")
    public String saveProduct(HttpServletRequest servletRequest,
                              @ModelAttribute Product product, BindingResult bindingResult,
                              Model model) {

        List<MultipartFile> files = product.getImages();

        List<String> fileNames = new ArrayList<String>();

        if (null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {

                String fileName = multipartFile.getOriginalFilename();
                System.out.println(fileName);
                fileNames.add(fileName);
                String rootpath = servletRequest.getSession().getServletContext().getRealPath("/");
                System.out.println(rootpath);
                String imageDir = servletRequest.getSession().getServletContext().getRealPath("/image/");

                String imageFileName = imageDir + fileName;
                System.out.println(imageFileName);
                File imageFile = new File(imageFileName);
                File parent = imageFile.getParentFile();
                if(!parent.exists())
                    parent.mkdirs();

                try {
                    multipartFile.transferTo(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // save product here
        model.addAttribute("product", product);
        return "ProductDetails";
    }

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Map<String, Object> model = new HashMap<String, Object>();
        if (e instanceof MaxUploadSizeExceededException) {
            System.out.println("====================MaxUploadSizeExceededException");
            model.put("errors", e.getMessage());
        } else {
            model.put("errors", "Unexpected error: " + e.getMessage());
        }
        model.put("product", new Product());
        return new ModelAndView("ProductForm", model);
    }

//    @ExceptionHandler(IOException.class)
//    public String handleIOException(IOException ex, HttpServletRequest request) {
//        return ClassUtils.getShortName(ex.getClass());
//    }
}
