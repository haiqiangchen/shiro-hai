package com.haiqiang.shiro.controller;

import com.haiqiang.shiro.service.UserInfoServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static jdk.nashorn.internal.objects.NativeDebug.map;

/**
 * @author haiqiang
 * @date 2018/7/19 11:53
 */
@Controller
public class LoginController {
    @Autowired
    private UserInfoServive userInfoServive;

    @RequestMapping({"","/index"})
    public String index(){
        return"/index";
    }

    @RequestMapping("/login")
    public String login(Map<String,Object> map){

        map.put("nothing","没有该账户");
        return "login";
    }
    @RequestMapping("/403")
    public String unauthorizedUrl(){
        return "403";
    }
}
