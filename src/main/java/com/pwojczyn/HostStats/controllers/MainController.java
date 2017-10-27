package com.pwojczyn.HostStats.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @GetMapping("/")
    public String home1() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }


    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

//    @GetMapping("/500")
//    public String error500() {
//        return "/error/500";
//    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }




}
