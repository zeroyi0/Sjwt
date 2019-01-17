package com.shiro.sjwtm.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.shiro.sjwtm.common.utils.JWTUtils;
import com.shiro.sjwtm.dao.entity.Permission;
import com.shiro.sjwtm.dao.entity.Role;
import com.shiro.sjwtm.dao.entity.User;
import com.shiro.sjwtm.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) principalCollection.getPrimaryPrincipal();
        String userName = JWTUtils.getUserName(token);
        if (userName == null) {
            return null;
        }

        User user = userService.getUserByUserName(userName);
        if (user == null) {
            return null;
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            info.addRole(role.getRoleName());       // 添加角色
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                info.addStringPermission(permission.getPermission());   // 添加权限
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        String userName = JWTUtils.getUserName(token);
        if (StringUtils.isEmpty(userName)) {
            throw new AuthenticationException("token认证失败！");
        }
        if (!JWTUtils.verify(token, userName)) {
            throw  new AuthenticationException("token认证还是失败");
        }
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            throw new AuthenticationException("用户不存在！");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
