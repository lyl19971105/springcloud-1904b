package com.jk.service;

import com.jk.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value="springcloud-provider-user",fallback = UserServiceError.class)
public interface UserService {

    @GetMapping("/queryUser")
    Map<String, Object> queryUser(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows);
    @GetMapping("/text")
    void text();
    @PostMapping("adUser")
    User adUser(@SpringQueryMap User user);

    @DeleteMapping("delUser")
    void delUser(@RequestParam("ids") String ids);
    @PutMapping("toUser")
    User toUser(@RequestParam("id")Integer id);
    @GetMapping("/toupUser")
    User toupUser(@RequestParam("id")Integer id);
    @GetMapping("/login1")
    String login1(@SpringQueryMap User user,@RequestParam("checkcode") String checkcode, @RequestParam("request") javax.servlet.http.HttpServletRequest request);


}
