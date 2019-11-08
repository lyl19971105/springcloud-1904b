package com.jk.controller;

import com.jk.model.User;
import com.jk.service.UserService;
import com.jk.service.UserServiceFeign;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController implements UserService {

    @Resource
    private UserServiceFeign userServiceFeign;

    @GetMapping("/queryUser")
    @ResponseBody
    @Override
    public Map<String, Object> queryUser( Integer page,Integer rows) {
        return userServiceFeign.queryUser(page,rows);
    }
    @GetMapping("/text")
    @ResponseBody
    @Override
    public void text() {
        System.out.println("调用成功！！！");
    }
    @PostMapping("adUser")
    @ResponseBody
    @Override
    public User adUser(User user) {
        return userServiceFeign.adUser(user);
    }
    @DeleteMapping("delUser")
    @ResponseBody
    @Override
    public void delUser(String ids) {
        userServiceFeign.delUser(ids);
    }
    @PutMapping("toUser")
    @Override
    public User toUser(Integer id) {
        return userServiceFeign.toUser(id);
    }
    @GetMapping("/toupUser")
    @Override
    public User toupUser(Integer id) {
        return userServiceFeign.toupUser(id);
    }


    @GetMapping("login1")
    public String login1(User user, String checkcode, HttpServletRequest request){
        String sessionCheckcode = (String) request.getSession().getAttribute("checkcode");
        //首先判断验证码是否正确
        if(checkcode.toLowerCase().equals(sessionCheckcode.toLowerCase())){
            return userServiceFeign.login1(user,request);
        }else if(checkcode==null ||checkcode==""){
            return "请输入验证码！！";
        }else{
            return "验证码错误！！！";//代表验证码错误
        }
    }
}
