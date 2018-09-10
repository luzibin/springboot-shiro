package com.weepal.flow.common.security;


import com.weepal.flow.common.entity.WpSysUser;
import com.weepal.flow.common.service.WpSysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 登录认证实现、链接权限的实现
 *
 * 1、
 *      通过Realm来获取应用程序中的用户、角色及权限信息
 * 2、
 *      Realm是专用于安全框架的DAO. Shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo(token)方法
 */
public class MyShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private WpSysUserService wpSysUserService;

    /**
     * 链接权限的实现
     *
     *    1、
     *      1.shiro的权限授权是通过继承AuthorizingRealm抽象类，重载doGetAuthorizationInfo()
     *      2如果只是简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回null即可
     *
     *    2、
     *      1.如果在shiro配置文件中添加了filterChainDefinitionMap.put(“/add”, “perms[权限添加]”);
     *          就说明访问/add这个链接必须要有“权限添加”这个权限才可以访问，
     *      2.如果在shiro配置文件中添加了filterChainDefinitionMap.put(“/add”, “roles[100002]，perms[权限添加]”);
     *          就说明访问/add这个链接必须要有“权限添加”这个权限和具有“100002”这个角色才可以访问。
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       logger.info("权限配置-->MyShiroRealm2.doGetAuthorizationInfo()");
        Object primaryPrincipal = principals.getPrimaryPrincipal();

        Set<String> roles= new HashSet<>();
        Set<String> permissions = new HashSet<>();
        roles.add("user");
        permissions.add("user:query");
        if ("admin".equals(primaryPrincipal)){
            roles.add("admin");
            permissions.add("admin:query");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 登录认证实现
     *
     *  1、检查提交的进行认证的令牌信息
     *  2、根据令牌信息从数据源(通常为数据库)中获取用户信息
     *  3、对用户信息进行匹配验证。
     *  4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
     *  5、验证失败则抛出AuthenticationException异常信息。
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        //获取用户
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String username = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());

        //从数据库获取用户
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        WpSysUser user = wpSysUserService.getUserInfo(username);

        //过滤非合法用户
        if(user == null){
            logger.info("该用户不存在");
            return null;
        }
       /* if(!password.equals(user.getPassword())){
            logger.info("密码有误");
        }*/

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username,user.getPassword(),this.getName());

        return authenticationInfo;
    }

}