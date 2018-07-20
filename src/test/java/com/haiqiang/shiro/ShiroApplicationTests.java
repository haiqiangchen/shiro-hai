package com.haiqiang.shiro;

import com.haiqiang.shiro.entity.User;
import com.haiqiang.shiro.service.UserInfoServive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {
    @Autowired
    private UserInfoServive userInfoServive;
    @Test
    public void contextLoads() {
        List<User> userList=userInfoServive.findByUsername("admin");
        System.out.println(userList);
}

}
