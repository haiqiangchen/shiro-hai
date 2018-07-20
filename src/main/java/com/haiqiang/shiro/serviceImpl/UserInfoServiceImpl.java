package com.haiqiang.shiro.serviceImpl;

import com.haiqiang.shiro.DAO.UserInfoDAO;
import com.haiqiang.shiro.entity.User;
import com.haiqiang.shiro.service.UserInfoServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haiqiang
 * @date 2018/7/19 11:45
 */
@Service
public class UserInfoServiceImpl implements UserInfoServive {
    @Autowired
    private UserInfoDAO userInfoDAO;
    @Override
    public List<User> findByUsername(String userName) {
        return userInfoDAO.findByUsername(userName);
    }
}
