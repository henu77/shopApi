package cn.malong.shopApi.controller;

import cn.malong.shopApi.entity.UserLoginInfo;
import cn.malong.shopApi.service.UserService;
import cn.malong.shopApi.vo.LoginSuccessVO;
import com.itheima.pinda.base.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.itheima.pinda.base.R.success;

/**
 * @author marlone
 * @Date 2022/8/9 15:11
 */
@RestController
@Api(tags = "用户")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(httpMethod = "POST", value = "用户登录接口")
    @PostMapping("/userlogin")
    public R<LoginSuccessVO> userLogin(@RequestBody UserLoginInfo userLoginInfo) {
        return userService.login(userLoginInfo);
    }
}
