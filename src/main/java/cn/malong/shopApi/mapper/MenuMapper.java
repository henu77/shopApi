package cn.malong.shopApi.mapper;

import cn.malong.shopApi.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author marlone
 * @Date 2022/8/12 19:24
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("select tb_menu.*\n" +
            "from tb_role_menu,\n" +
            "     tb_menu\n" +
            "where tb_role_menu.role_id = #{roleId} && tb_role_menu.menu_id = tb_menu.id")
    List<Menu> getMenuListByRoleId(int roleId);
}
