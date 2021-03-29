package org.yuzhuang.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yuzhuang.mall.common.service.RedisService;
import org.yuzhuang.mall.mapper.UmsAdminMapper;
import org.yuzhuang.mall.model.UmsAdmin;
import org.yuzhuang.mall.model.UmsResource;
import org.yuzhuang.mall.service.UmsAdminCacheService;
import org.yuzhuang.mall.service.UmsAdminService;

import java.util.List;

/**
 * @author yuzhuangzhuang
 * @create 2021/03/05 10:38
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsAdminService umsAdminService;

    @Autowired
    private RedisService redisService;

    @Value("${app.redis.database}")
    private String REDIS_DATABASE;

    @Value("${app.redis.key.admin}")
    private String REDIS_KEY_PREFIX_ADMIN;

    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin umsAdmin = umsAdminService.getAdminById(adminId);
        if (umsAdmin != null){
            String key = REDIS_DATABASE + ":" +REDIS_KEY_PREFIX_ADMIN + ":" + umsAdmin.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long adminId) {

    }

    @Override
    public void delResourceListByRole(Long roleId) {

    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {

    }

    @Override
    public void delResourceListByResource(Long resourceId) {

    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" +REDIS_KEY_PREFIX_ADMIN + ":" +username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {

    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return null;
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {

    }
}
