package org.yuzhuang.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yuzhuangzhuang
 * @create 2021/02/24 10:31
 * org.yuzhuang.mall.mapper: 自动生成的通用接口, 一般用于当前表的单表操作
 * org.yuzhuang.mall.dao: 自定义接口
 */
@Configuration
@EnableTransactionManagement
@MapperScan(value = {"org.yuzhuang.mall.mapper", "org.yuzhuang.mall.dao"})
public class MyBatisConfig {
}
