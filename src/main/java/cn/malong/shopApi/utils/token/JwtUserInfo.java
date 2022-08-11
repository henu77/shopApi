package cn.malong.shopApi.utils.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author marlone
 * @Date 2022/8/11 22:40
 * jwt 存储的 内容
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "JWT令牌中存储的信息")
public class JwtUserInfo implements Serializable {
    @ApiModelProperty(value = "JWT令牌中存储的用户名")
    private String username;
    @ApiModelProperty(value = "JWT令牌中存储的用户角色")
    private int role;
}
