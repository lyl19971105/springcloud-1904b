package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.User;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserServiceFeign{
    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, Object> queryUser(Integer page, Integer rows) {
        Long count=userMapper.queryUserCount();
        List<User> list=userMapper.queryUser((page-1)*rows,rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total",count);
        map.put("rows",list);
        return map;
    }

    @Override
    public User adUser(User user) {
        return userMapper.adUser(user);
    }

    @Override
    public void delUser(String ids) {

        userMapper.delUser(ids);
    }

    @Override
    public User toUser(Integer id) {
        return userMapper.toUser(id);
    }

    @Override
    public User toupUser(Integer id) {
        return userMapper.toupUser(id);
    }

    public String login1(User user, HttpServletRequest request) {
        /*	String hql = "from User where userName = '"+user.getUserName()+"' and userPwd = '"+user.getUserPwd()+"'";*/
        try {
            //确定md5计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //获取base64转化编码的对象
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            String newstr = base64en.encode(md5.digest(user.getUserPwd().getBytes("utf-8")));
            //String newstr3 = base64en.encode(md5.digest(user.getUserName().getBytes("utf-8")));
            //user.setUserName(newstr3);
            user.setUserPwd(newstr);
            System.out.println(newstr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        User reUser = userMapper.queryUserByHql(user);
        if(reUser==null){
            return "账号或密码错误！！";//账号或者密码错误，登录失败
        }else{
            request.getSession().setAttribute("loginUser", reUser);
            return "登陆成功！！";//登录成功
        }
    }

}
