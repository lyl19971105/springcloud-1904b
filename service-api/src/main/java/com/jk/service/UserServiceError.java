package com.jk.service;

import com.jk.model.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Component
public class UserServiceError implements UserService {
    public Map<String, Object> queryUser(Integer page, Integer rows) {
        System.out.println(page+rows+"--------------------------------");
        return null;
    }

    public void text() {
        System.out.println("测试方法请求失败！！");
    }

    public User adUser(User user) {
        return user;
    }

    public void delUser(String ids) {
        System.out.println("删除失败！！！" +
                "");
    }

    public User toUser(Integer id) {
        User user=new User();
        System.out.println("测试失败！！");
        return user;
    }

    public User toupUser(Integer id) {
        User user=new User();
        System.out.println("测试失败！！");
        return user;
    }

    public String login1(User user, String checkcode, HttpServletRequest request) {
        System.out.println("登录失败！！！");
        return null;
    }
}
