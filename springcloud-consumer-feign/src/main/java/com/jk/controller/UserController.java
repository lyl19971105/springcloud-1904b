package com.jk.controller;

import com.jk.model.User;
import com.jk.service.UserService;
import com.jk.until.CheckImgUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/queryUser")
    @ResponseBody
    public Map<String,Object> queryUser(Integer page,Integer rows){
     return userService.queryUser(page,rows);
 }
 @RequestMapping("text")
 @ResponseBody
    public void text(){
     userService.text();
 }
    @RequestMapping("/adUser")
    @ResponseBody
    public User adUser(User user){
        return userService.adUser(user);
    }
    @RequestMapping("/delUser")
    @ResponseBody
    public void delUser(String ids){
        userService.delUser(ids);
    }
    @RequestMapping("toUser")
    public String toUser(Model model,Integer id){
        User user=userService.toUser(id);
        model.addAttribute("u",user);
        return "main/toUser";
    }
    @RequestMapping("toupUser")
    public String toupUser(Integer id,Model model){
        User user=userService.toupUser(id);
        model.addAttribute("u",user);
        return "main/toUser";
    }
    @RequestMapping("checkImg")
    public void checkImg(HttpServletRequest request, HttpServletResponse response){
        CheckImgUtil.buildCheckImg(request, response);
    }

    @RequestMapping("login1")
    @ResponseBody
    public String login1(User user,String checkcode,HttpServletRequest request){
            return userService.login1(user,checkcode,request);


    }




}
