package cn.malong.shopApi.service.imp;

import cn.malong.shopApi.entity.Menu;
import cn.malong.shopApi.entity.User;
import cn.malong.shopApi.entity.UserLoginInfo;
import cn.malong.shopApi.mapper.MenuMapper;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private MenuMapper menuMapper;

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
                //获取 token
                Token userToken = jwtTokenUtils.generateUserToken(
                        new JwtUserInfo(userByUserName.getUsername(), userByUserName.getRole()), JWT_TOKEN_EFFECTIVE_TIME);
                LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
                loginSuccessVO.setToken(userToken.getToken());
                loginSuccessVO.setUsername(userLoginInfo.getUsername());
                loginSuccessVO.setRoleid(userByUserName.getRole());
                // 根据用户角色获取菜单权限
                int roleId = userByUserName.getRole();
                List<Menu> menuList = menuMapper.getMenuListByRoleId(roleId);
                List<Menu> menus = t1(menuList);
                loginSuccessVO.setMenus(menus);
                return R.success(loginSuccessVO, LOGIN_SUCCESS);
            } else {
                return R.fail(LOGIN_WRONG_PASSWORD);
            }
        } else {
            return R.fail(LOGIN_CAPTCHA_FAIL);
        }
    }

    private List<Menu> t1(List<Menu> menus) {
        List<Menu> rootMenuList = menus.stream()
                .filter(item -> 0 == item.getPid())
                .collect(Collectors.toList());
//        log.info("最顶级菜单列表为={}", rootMenuList);
        List<Menu> menuList = new ArrayList<>(1);
        //4.递归思想，查找所有的子菜单树
        rootMenuList.stream().forEach(rootMenu -> {
            rootMenu.setChildren(getChild(menus, rootMenu.getId()));
            menuList.add(rootMenu);
        });
        return menuList;
    }

    private List<Menu> getChild(List<Menu> menus, int id) {
        //1.根据uId查找该菜单的所有子菜单
        List<Menu> children = new ArrayList<>(10);
        menus.stream().forEach(item -> {
            //判断此节点是否为该菜单的子菜单
            if (id == item.getPid()) {
                children.add(item);
            }
        });
//        log.info("排序前={}", children);
        //2.给子菜单排序
        if (!children.isEmpty()) {
            //自定义集合排序
            Collections.sort(children, (o1, o2) -> {
                //从小到大
                return o1.getSort() > o2.getSort()
                        ? 1 : (o1.getSort() == o2.getSort() ? 0 : -1);
            });
        }
        log.info("排序后={}", children);
        //3.遍历所有子菜单，递归查找所有子菜单
        children.stream().forEach(item -> item.setChildren(getChild(menus, item.getId())));
        return children;
    }
}
