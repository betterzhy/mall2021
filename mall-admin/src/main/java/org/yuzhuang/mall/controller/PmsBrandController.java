package org.yuzhuang.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yuzhuang.mall.api.CommonResult;
import org.yuzhuang.mall.dto.PmsBrandParam;
import org.yuzhuang.mall.model.PmsBrand;
import org.yuzhuang.mall.service.PmsBrandService;

import java.util.List;

/**
 * @author yuzhuangzhuang
 * @create 2021/02/24 10:16
 */
@RestController
@RequestMapping("/pms/brands")
@Api(tags = "商品品牌管理", description = "PmsBrandController")
public class PmsBrandController {
    private final PmsBrandService pmsBrandService;

    public PmsBrandController(PmsBrandService pmsBrandService) {
        this.pmsBrandService = pmsBrandService;
    }

    @GetMapping
    @ApiOperation("获取品牌列表")
    private CommonResult<List<PmsBrand>> getBrands(@RequestParam(value = "keyword", required = false)  String keyword,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return CommonResult.success(pmsBrandService.getBrands(keyword, pageNum, pageSize));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取品牌")
    private CommonResult<PmsBrand> getBrandById(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.getBrandById(id));
    }

    @PostMapping
    @ApiOperation("新增品牌")
    private CommonResult<Integer> createBrand(@Validated @RequestBody PmsBrandParam pmsBrandparam) {
        int count = pmsBrandService.createBrand(pmsBrandparam);
        if (count == 1) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新品牌")
    private CommonResult<Integer> updateBrand(@PathVariable Long id,
                                              @Validated @RequestBody PmsBrandParam pmsBrandparam) {
        int count = pmsBrandService.updateBrand(id, pmsBrandparam);
        if (count == 1) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除品牌")
    private CommonResult<Integer> deleteBrandById(@PathVariable("id") Long id) {
        int count = pmsBrandService.deleteBrandById(id);
        if (count == 1) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
