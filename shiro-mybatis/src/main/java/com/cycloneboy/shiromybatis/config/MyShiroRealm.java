package com.cycloneboy.shiromybatis.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cycloneboy.shiromybatis.entity.*;
import com.cycloneboy.shiromybatis.service.*;
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
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-10-28 10:22
 **/
public class MyShiroRealm  extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        User user  = (User)principalCollection.getPrimaryPrincipal();
        List<UserRole> roleList = userRoleService.list(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, user.getId()));

        for(UserRole userRole: roleList){
            authorizationInfo.addRole(roleService.getById(userRole.getRoleId()).getRole());

            List<RolePermission> rolePermissionList = rolePermissionService.list(new QueryWrapper<RolePermission>()
                .lambda().eq(RolePermission::getRoleId,userRole.getRoleId()));

            for(RolePermission rolePermission: rolePermissionList){
                authorizationInfo.addStringPermission(permissionService.getById(rolePermission.getPermissionId()).getPermission());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)authenticationToken.getPrincipal();
        System.out.println("authenticationToken: " + authenticationToken.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username)) ;

        System.out.println("----->>user="+user);
        if(user == null){
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        System.out.println("authenticationInfo: " + authenticationInfo.toString());
        return authenticationInfo;
    }
}
