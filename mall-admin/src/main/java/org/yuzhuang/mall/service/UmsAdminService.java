package org.yuzhuang.mall.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.yuzhuang.mall.dto.UmsAdminParam;
import org.yuzhuang.mall.model.UmsAdmin;
import org.yuzhuang.mall.model.UmsResource;

import java.util.List;

/**
 * @author yuzhuangzhuang
 * @create 2021/03/05 10:18
 */
public interface UmsAdminService {
    UmsAdmin getAdminByUsername(String username);

    /**
     * 根据id获取用户
     */
    UmsAdmin getAdminById(Long adminId);

    /**
     * 用户注册
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 用户登录
     */
    String login(String username, String password);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);
    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
