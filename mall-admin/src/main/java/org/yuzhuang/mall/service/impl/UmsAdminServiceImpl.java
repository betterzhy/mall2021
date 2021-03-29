package org.yuzhuang.mall.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yuzhuang.mall.common.exception.ApiException;
import org.yuzhuang.mall.dao.AdminRoleRelationDao;
import org.yuzhuang.mall.dto.UmsAdminParam;
import org.yuzhuang.mall.mapper.UmsAdminMapper;
import org.yuzhuang.mall.model.UmsAdmin;
import org.yuzhuang.mall.model.UmsAdminExample;
import org.yuzhuang.mall.model.UmsResource;
import org.yuzhuang.mall.security.util.JwtTokenProvider;
import org.yuzhuang.mall.bo.AdminUserDetails;
import org.yuzhuang.mall.service.UmsAdminCacheService;
import org.yuzhuang.mall.service.UmsAdminService;

import java.util.Date;
import java.util.List;

/**
 * @author yuzhuangzhuang
 * @create 2021/03/05 10:19
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private AdminRoleRelationDao adminRoleRelationDao;

    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        // 缓存中获取
        UmsAdmin admin = adminCacheService.getAdmin(username);
        if (admin != null) return admin;
        // 数据库中获取
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = umsAdminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            // 加入缓存中
            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public UmsAdmin getAdminById(Long adminId) {
        return umsAdminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        // 查询是否有相同用户名
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(umsAdminExample);
        if (umsAdmins.size() > 0) {
            return null;
        }
        // 添加默认创建时间和状态
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        // 密码加密
        umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new ApiException("密码不正确");
        }
        if (!userDetails.isEnabled()) {
            throw new ApiException("账号已被禁用");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return jwtTokenProvider.generateToken(username);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return adminRoleRelationDao.getResourceList(adminId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
