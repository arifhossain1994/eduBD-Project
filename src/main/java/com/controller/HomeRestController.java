package com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeRestController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/SuperAdminHome")// show the home page
    public String homePage(Model model) {
        return "superAdminHome"; // this is the page that is rendering home.html
    }







}