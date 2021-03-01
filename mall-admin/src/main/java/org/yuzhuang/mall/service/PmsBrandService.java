package org.yuzhuang.mall.service;

import org.springframework.transaction.annotation.Transactional;
import org.yuzhuang.mall.dto.PmsBrandParam;
import org.yuzhuang.mall.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {
    /**
     *  获取品牌列表
     */
    List<PmsBrand> getBrands(String keyword, int pageNum, int pageSize);

    /**
     *  获取品牌
     */
    PmsBrand getBrandById(Long id);

    /**
     *  新增品牌
     */
    int createBrand(PmsBrandParam brand);

    /**
     *  更新品牌
     */
    @Transactional
    int updateBrand(Long id, PmsBrandParam brand);

    /**
     *  删除品牌
     */
    int deleteBrandById(Long id);
}
