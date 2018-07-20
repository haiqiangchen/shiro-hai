package com.haiqiang.shiro.service;

import com.haiqiang.shiro.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserInfoServive {
    /**
     * 服务层的接口
     *
     */
    List<User> findByUsername(String userName);
}
