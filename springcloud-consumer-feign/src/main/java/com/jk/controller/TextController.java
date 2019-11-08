package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TextController {
    @RequestMapping("main")
    public String main(){
        return "main/showUser";
    }
    @RequestMapping("addUser")
    public String addUser(){
        return "main/addUser";
    }
    @RequestMapping("login")
    public String login(){
        return "login/login";
    }
    @RequestMapping("zhuce")
    public String zhuce(){
        return "login/zhuce";
    }

}
