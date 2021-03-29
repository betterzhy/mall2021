package org.yuzhuang.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuzhuang.mall.common.api.CommonResult;
import org.yuzhuang.mall.dto.UmsAdminLoginParam;
import org.yuzhuang.mall.dto.UmsAdminParam;
import org.yuzhuang.mall.model.UmsAdmin;
import org.yuzhuang.mall.service.UmsAdminService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhuangzhuang
 * @create 2021/03/05 10:10
 */
@RestController
@RequestMapping("/admin/")
@Api(tags = "后台用户管理", description = "UmsAdminController")
public class UmsAdminController {
    @Autowired
    private UmsAdminService umsAdminService;

    @Value("${app.jwt.token-header}")
    private String JWT_TOKEN_HEADER;

    @Value("${app.jwt.token-schema}")
    private String JWT_TOKEN_SCHEMA;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = umsAdminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed("用户名已存在");
        }
        return CommonResult.success(umsAdmin);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public CommonResult<Map<String, String>> login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        String token = umsAdminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.failed();
        }
        Map<String, String> resp = new HashMap<>();
        // 携带jwt形式 Authentication: Bearer <JWT>
        resp.put("tokenHeader", JWT_TOKEN_HEADER);
        resp.put("tokenSchema", JWT_TOKEN_SCHEMA);
        resp.put("token", token);
        return CommonResult.success(resp);
    }
}
