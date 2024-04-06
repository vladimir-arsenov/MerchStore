package com.merchstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {
    @GetMapping("/user")
    public ModelAndView toUserProfile() {
        ModelAndView modelAndView = new ModelAndView("user_profile");
        return modelAndView;
    }
}
