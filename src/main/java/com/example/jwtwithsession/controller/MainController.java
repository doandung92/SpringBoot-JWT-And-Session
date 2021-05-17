package com.example.jwtwithsession.controller;

import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String admin(){
        return "admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String user(){
        return "user";
    }

    @GetMapping("/access-denied")
    public String deniedPage(){
        return "access-denied";
    }
}
