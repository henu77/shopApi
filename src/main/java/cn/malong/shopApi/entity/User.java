package cn.malong.shopApi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marlone
 * @Date 2022/8/9 1:30
 */
@Data
@ApiModel(description = "用户实体")
@TableName(value = "tb_user")
public class User {
    @ApiModelProperty(value = "用户名")
    @TableField(value = "username")
    private String username;
    @ApiModelProperty(value = "昵称")
    @TableField(value = "nickname")
    private String nickname;
    @ApiModelProperty(value = "密码")
    @TableField(value = "password")
    private String password;
    @TableField(value = "age")
    @ApiModelProperty(value = "年龄")
    private int age;
    @TableField(value = "role")
    @ApiModelProperty(value = "角色,0:普通用户；1：管理员；99：超级管理员")
    private int role;
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;
}
