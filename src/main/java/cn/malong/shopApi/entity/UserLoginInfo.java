package cn.malong.shopApi.entity;

import com.itheima.pinda.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marlone
 * @Date 2022/8/9 22:36
 */
@Data
@ApiModel(description = "前端用户登录传来的数据")
public class UserLoginInfo {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码校验是否通过")
    private boolean captchaSuccess;
}
