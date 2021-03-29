package org.yuzhuang.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author yuzhuangzhuang
 * @create 2021/03/18 23:18
 */
@Getter
@Setter
public class UmsAdminLoginParam {
    @NotBlank
    @Size(max = 15)
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank
    @Size(max = 32)
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
