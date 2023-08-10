package com.xiao.mall.service.ums;

import com.xiao.mall.common.api.CommonResult;
import com.xiao.mall.security.entity.SecurityUser;
import com.xiao.mall.security.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private User testUser;

    public UserDetailsServiceImpl(){
        //模拟用户
        testUser = User.builder().username("xiao").password("96e79218965eb72c92a549dd5a330112").build();
    }


    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 判断用户是否存在
        if (!testUser.getUsername().equals(username)){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        User curUser = new User();
        BeanUtils.copyProperties(testUser, curUser);

        List<String> authorities = new ArrayList<>();
        //模拟权限
        authorities.add("security.auth");
        SecurityUser securityUser = new SecurityUser(curUser);
        //这里必须返回权限列表，否则登录校验失败
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }
}
