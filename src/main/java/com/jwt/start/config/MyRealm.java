package com.jwt.start.config;

import com.jwt.start.model.AccessToken;
import com.jwt.start.model.UserInfo;
import com.jwt.start.repository.UserInfoRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/9  16:27
 */

public class MyRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);
    @Autowired
    private UserInfoRepository service;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AccessToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        /*String username = JWTUtil.getUsername(principals.toString());*/
        String username = "admin";
        System.out.println("当需要权限的时候 就会调用我了");
        UserInfo user = service.findUserInfoByName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRole());
       /* Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
        simpleAuthorizationInfo.addStringPermissions(permission);*/
       simpleAuthorizationInfo.addStringPermissions(null);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比

        System.out.println(token + "最开始的授权过程");

        String username = "admin";

        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        UserInfo userBean = service.findUserInfoByName(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        String password = "123456";

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
