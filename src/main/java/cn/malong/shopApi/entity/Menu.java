package cn.malong.shopApi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author marlone
 * @Date 2022/8/11 17:35
 */
@ApiModel(description = "后台菜单")
@Data
@TableName(value = "tb_menu")
public class Menu {
    private int id;
    private int pid;
    private String icon;
    private int status;
    private String title;
    private int type;
    private String url;
    private int sort;
    private List<Menu> children;
}
