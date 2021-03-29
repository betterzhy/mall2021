package org.yuzhuang.mall;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yuzhuang.mall.security.config.IgnoreAuthUrlsConfig;
import org.yuzhuang.mall.security.util.JwtTokenProvider;

/**
 * @author yuzhuangzhuang
 * @create 2021/03/01 16:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MallAdminApplicationTest {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IgnoreAuthUrlsConfig ignoreAuthUrlsConfig;

    @Test
    public void JwtTokenProviderTest() {
        String username = "mongs";
        String token = jwtTokenProvider.generateToken(username);
        boolean valid = jwtTokenProvider.validateToken(token);
        Assertions.assertTrue(valid);
        Assertions.assertEquals(jwtTokenProvider.getUsernameFromToken(token), username);
    }

    @Test
    public void getIgnoreUrls(){
        for (String url : ignoreAuthUrlsConfig.getUrls()) {
            System.out.println(url);
        }
    }

}
