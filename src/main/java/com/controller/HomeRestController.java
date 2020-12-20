package com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeRestController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping ("/ManageSchool")
    public String manageSchoolPage(Model model){
        return "manageSchool";
    }

    @GetMapping("/school")
    public String createSchoolForm(Model model){
        return "createSchoolForm";
    }
}