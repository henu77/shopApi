package cn.malong.shopApi.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static cn.malong.shopApi.properties.AuthServerProperties.PREFIX;

/**
 * @author marlone
 * @Date 2022/8/11 23:43
 */
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = PREFIX)
public class AuthServerProperties {
    public static final String PREFIX = "authentication";

    private TokenInfo user;

    @Data
    public static class TokenInfo {
        /**
         * 过期时间
         */
        private Integer expire = 7200;
        /**
         * 加密 服务使用
         */
        private String priKey;
        /**
         * 解密
         */
        private String pubKey;
    }

}

