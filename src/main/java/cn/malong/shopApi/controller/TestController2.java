package cn.malong.shopApi.controller;

import cn.malong.shopApi.entity.User;
import cn.malong.shopApi.mapper.UserMapper;
import com.itheima.pinda.base.R;
import com.itheima.pinda.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.itheima.pinda.base.R.success;

/**
 * @author marlone
 * @Date 2022/8/9 1:20
 */

@RestController
@Api(tags = "测试")
public class TestController2 {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/get")
    @ApiOperation(value = "测试xss", notes = "测试")
    public String get123(String text) {
        User user = new User();
        return "处理之后的文本内容为：" + text;
    }

    @GetMapping("/getUser")
    @ApiOperation(value = "a", notes = "测试")
    public User getUser(String text2) {
        User user = new User();
//        int a=1/0;
        userMapper.selectList(null);
        return user;
    }

    @GetMapping("/mybatisplus")
    @ApiOperation(value = "a", notes = "测试")
    public R<List<User>> getUser2(String text2) {
        List<User> userList = userMapper.selectList(null);

        return success(userList);
    }

    @SysLog("分页查询用户")//记录操作日志
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码",
                    required = true, type = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",
                    required = true, type = "Integer"),
    })
    @ApiOperation(value = "分页查询用户信息")
    @GetMapping(value = "page/{pageNum}/{pageSize}")
    public String findByPage(@PathVariable Integer pageNum,
                             @PathVariable Integer pageSize) {
        return "OK";
    }
}
