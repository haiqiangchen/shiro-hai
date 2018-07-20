package com.haiqiang.shiro.web;

import com.haiqiang.shiro.entity.User;
import com.haiqiang.shiro.entity.Permission;
import com.haiqiang.shiro.entity.Role;
import com.haiqiang.shiro.service.UserInfoServive;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author haiqiang
 * @date 2018/7/19 11:56
 */

public class shiroDBRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoServive userInfoServive;
    /**
     * 重新定义授权的方式
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("ShiroRealm.doGetAuthorizationInfo()--权限配置");
        /**
         * 从用户的信息中读取用户的相关权限，获取权限之后，将权限封装在SimpleAuthorizationInfo
         */
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User userInfo  = (User)principalCollection.getPrimaryPrincipal();
        for(Role role:userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(Permission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 重新定义登录认证的方式
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("shiroDBRealm.doGetAuthenticationInfo()--登录认证");
        /**
         * 获取用户的登录信息，与数据库的信息进行比较，比对正确之后，将用户的信息进行缓存，方便下次登录
         */
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        List<User> userInfoList = userInfoServive.findByUsername(username);
        System.out.println("----->>userInfo="+userInfoList);
        if(userInfoList.get(0) == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfoList.get(0), //用户名
                userInfoList.get(0).getPassword(), //密码
                ByteSource.Util.bytes(userInfoList.get(0).getCredentialsSalt()),
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
