package cn.malong.shopApi.utils.token;

import cn.malong.shopApi.properties.AuthServerProperties;
import com.itheima.pinda.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author marlone
 * @Date 2022/8/11 22:41
 */
@AllArgsConstructor
@Component
@Slf4j
public class JwtTokenUtils {
    /**
     * 认证服务端使用，如 authority-server
     * 生成和 解析token
     */
    private AuthServerProperties authServerProperties;

    /**
     * 生成token
     *
     * @param jwtInfo
     * @param expire
     * @return
     * @throws BizException
     */
    public Token generateUserToken(JwtUserInfo jwtInfo, Integer expire) throws BizException {
        AuthServerProperties.TokenInfo userTokenInfo = authServerProperties.getUser();
        if (expire == null || expire <= 0) {
            expire = userTokenInfo.getExpire();
        }
        return JwtHelper.generateUserToken(jwtInfo, userTokenInfo.getPriKey(), expire);
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     * @throws BizException
     */
    public JwtUserInfo getUserInfo(String token) throws BizException {
        AuthServerProperties.TokenInfo userTokenInfo = authServerProperties.getUser();
        return JwtHelper.getJwtFromToken(token, userTokenInfo.getPubKey());
    }
}
