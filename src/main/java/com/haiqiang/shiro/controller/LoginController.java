package com.haiqiang.shiro.controller;

import com.haiqiang.shiro.service.UserInfoServive;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    /**
     * 主页面
     * @return
     */
    @RequestMapping({"","/index"})
    public String index(){
        return"index";
    }

    /**
     * 登录页面
     * @param map
     * @return
     */
    @RequestMapping("/login")
    public String login(Map<String,Object> map){

        map.put("nothing","没有该账户");
        return "login";
    }

    /**
     * 由访问权限的页面
     * @return
     */
    @RequestMapping("/userView")
    @RequiresPermissions("userInfo:view")
    public String View(){
        return "view";
    }

    /**
     * 有删除权限的页面
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")
    public String del(){
        return "Del";
    }

    @RequestMapping("/403")
    public String unauthorizedUrl(){
        return "403";
    }
}
