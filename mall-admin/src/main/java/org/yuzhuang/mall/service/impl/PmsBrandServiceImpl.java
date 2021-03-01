package org.yuzhuang.mall.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.yuzhuang.mall.dto.PmsBrandParam;
import org.yuzhuang.mall.mapper.PmsBrandMapper;
import org.yuzhuang.mall.mapper.PmsProductMapper;
import org.yuzhuang.mall.model.PmsBrand;
import org.yuzhuang.mall.model.PmsBrandExample;
import org.yuzhuang.mall.model.PmsProduct;
import org.yuzhuang.mall.model.PmsProductExample;
import org.yuzhuang.mall.service.PmsBrandService;

import java.util.List;

/**
 * @author yuzhuangzhuang
 * @create 2021/02/24 10:16
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;
    @Autowired
    private PmsProductMapper pmsProductMapper;

    @Override
    public List<PmsBrand> getBrands(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        PmsBrandExample.Criteria criteria = pmsBrandExample.createCriteria();
        if (!StringUtils.isEmpty(keyword)){
            criteria.andNameLike(keyword);
        }
        return pmsBrandMapper.selectByExample(pmsBrandExample);
    }

    @Override
    public PmsBrand getBrandById(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int createBrand(PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())){
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        return pmsBrandMapper.insertSelective(pmsBrand);
    }

    @Override
    public int updateBrand(Long id, PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        // 如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        // 更新品牌时要更新商品中的品牌名称
        PmsProduct pmsProduct = new PmsProduct();
        pmsProduct.setBrandName(pmsBrand.getName());
        PmsProductExample pmsProductExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = pmsProductExample.createCriteria();
        criteria.andIdEqualTo(id);
        pmsProductMapper.updateByExampleSelective(pmsProduct, pmsProductExample);
        return pmsBrandMapper.updateByPrimaryKeySelective(pmsBrand);
    }

    @Override
    public int deleteBrandById(Long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }
}
