package org.yuzhuang.mall.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yuzhuangzhuang
 * @create 2021/03/23 23:28
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "app.secure.ignored")
public class IgnoreAuthUrlsConfig {

    private String[] urls;

}