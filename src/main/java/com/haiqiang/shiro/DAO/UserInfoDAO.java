package com.haiqiang.shiro.DAO;

import com.haiqiang.shiro.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserInfoDAO extends CrudRepository<User,Long> {
    /**
     * DAO层的接口
     */
    List<User> findByUsername(String userName);

}

