package org.yuzhuang.mall.dao;

import org.apache.ibatis.annotations.Param;
import org.yuzhuang.mall.model.UmsAdminRoleRelation;
import org.yuzhuang.mall.model.UmsResource;
import org.yuzhuang.mall.model.UmsRole;

import java.util.List;

/**
 * @author yuzhuangzhuang
 * @create 2021/03/18 23:32
 */
public interface AdminRoleRelationDao {
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取资源相关用户ID列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}
