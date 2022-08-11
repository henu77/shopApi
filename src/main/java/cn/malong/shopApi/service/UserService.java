package cn.malong.shopApi.service;

import cn.malong.shopApi.entity.User;
import cn.malong.shopApi.entity.UserLoginInfo;
import cn.malong.shopApi.vo.LoginSuccessVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.pinda.base.R;

/**
 * @author marlone
 * @Date 2022/8/11 17:51
 */
public interface UserService extends IService<User> {
    R<LoginSuccessVO> login(UserLoginInfo userLoginInfo);
}
