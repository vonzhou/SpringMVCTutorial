package com.vonzhou.learning.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CityController {

    @RequestMapping(value = "/cities")
    public String listCities(Model model) {
        Map<String, String> capitals =
                new HashMap<String, String>();
        capitals.put("Indonesia", "Jakarta");
        capitals.put("Malaysia", "Kuala Lumpur");
        capitals.put("Thailand", "Bangkok");
        model.addAttribute("capitals", capitals);


        Map<String, String[]> bigCities =
                new HashMap<String, String[]>();
        bigCities.put("Australia", new String[]{"Sydney",
                "Melbourne", "Perth"});
        bigCities.put("New Zealand", new String[]{"Auckland",
                "Christchurch", "Wellington"});
        bigCities.put("Indonesia", new String[]{"Jakarta",
                "Surabaya", "Medan"});
        model.addAttribute("bigCities", bigCities);
        return "Cities";
    }
}