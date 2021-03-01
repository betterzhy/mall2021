package org.yuzhuang.mall.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yuzhuangzhuang
 * @create 2021/02/24 12:32
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private String code;
    private String message;
    private T data;

    public static <T> CommonResult<T> success(T data){
        return new CommonResult<>("200", "success", data);
    }

    public static <T> CommonResult<T> failed(){
        return new CommonResult<>("404", "internal server error", null);
    }
}
