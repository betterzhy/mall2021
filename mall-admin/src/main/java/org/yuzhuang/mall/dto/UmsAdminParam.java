package org.yuzhuang.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author yuzhuangzhuang
 * @create 2021/03/01 14:35
 */


@Getter
@Setter
public class UmsAdminParam {
    @NotEmpty
    @Size(max = 15)
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "用户头像")
    private String icon;

    @Email
    @Size(max = 40)
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户昵称")
    @Size(max = 15)
    private String nickName;

    @ApiModelProperty(value = "备注")
    @Size(max = 256)
    private String note;
}