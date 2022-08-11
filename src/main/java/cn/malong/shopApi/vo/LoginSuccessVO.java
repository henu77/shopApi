package cn.malong.shopApi.vo;

import cn.malong.shopApi.entity.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author marlone
 * @Date 2022/8/11 17:27
 */
@Data
@ApiModel(description = "登陆成功后返回给前端的数据")
public class LoginSuccessVO {
    @ApiModelProperty(value = "菜单列表")
    private List<Menu> menus;
    @ApiModelProperty(value = "菜单对应URL")
    private List<String> menus_url;
    @ApiModelProperty(value = "用户角色")
    private int roleid;
    @ApiModelProperty(value = "状态")
    private int status;
    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "用户名")
    private String username;
}
