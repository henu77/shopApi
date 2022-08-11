package cn.malong.shopApi.service.imp;

import cn.malong.shopApi.entity.User;
import cn.malong.shopApi.entity.UserLoginInfo;
import cn.malong.shopApi.mapper.UserMapper;
import cn.malong.shopApi.service.UserService;
import cn.malong.shopApi.utils.token.JwtTokenUtils;
import cn.malong.shopApi.utils.token.JwtUserInfo;
import cn.malong.shopApi.utils.token.Token;
import cn.malong.shopApi.vo.LoginSuccessVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.pinda.base.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.malong.shopApi.utils.Constants.*;

/**
 * @author marlone
 * @Date 2022/8/11 17:53
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public R<LoginSuccessVO> login(UserLoginInfo userLoginInfo) {
        // 滑动验证码校验
        if (userLoginInfo.isCaptchaSuccess()) {
            // 条件构造器
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", userLoginInfo.getUsername());
            User userByUserName = userMapper.selectOne(wrapper);
            if (null == userByUserName) {
                return R.fail(LOGIN_USERNAME_NOT_FOUND);
            }
            // 密码相同
            if (userByUserName.getPassword().equals(userLoginInfo.getPassword())) {
                Token userToken = jwtTokenUtils.generateUserToken(
                        new JwtUserInfo(userByUserName.getUsername(), userByUserName.getRole()), JWT_TOKEN_EFFECTIVE_TIME);
                LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
                loginSuccessVO.setToken(userToken.getToken());
                loginSuccessVO.setUsername(userLoginInfo.getUsername());
                loginSuccessVO.setRoleid(userByUserName.getRole());
                return R.success(loginSuccessVO,LOGIN_SUCCESS);
            } else {
                return R.fail(LOGIN_WRONG_PASSWORD);
            }
        } else {
            return R.fail(LOGIN_CAPTCHA_FAIL);
        }
    }
}
