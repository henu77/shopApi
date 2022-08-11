package cn.malong.shopApi.utils;

/**
 * @author marlone
 * @Date 2022/8/11 22:16
 */
public class Constants {
    public final static String LOGIN_CAPTCHA_FAIL = "请完成滑动验证码！";
    public final static String LOGIN_WRONG_PASSWORD = "密码错误！";
    public final static String LOGIN_USERNAME_NOT_FOUND = "用户名不存在！";
    public final static String LOGIN_SUCCESS = "登陆成功！";


    public final static int JWT_TOKEN_EFFECTIVE_TIME = 3600;
    public static final String JWT_KEY_ROLE = "role";
}
