package com.jk.service;

import com.jk.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserServiceFeign {
    Map<String, Object> queryUser(Integer page, Integer rows);

    User adUser(User user);

    void delUser(String ids);

    User toUser(Integer id);

    User toupUser(Integer id);

    String login1(User user, HttpServletRequest request);
}
