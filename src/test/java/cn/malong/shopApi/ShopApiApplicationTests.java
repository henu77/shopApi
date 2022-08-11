package cn.malong.shopApi;

import cn.malong.shopApi.utils.token.JwtTokenUtils;
import cn.malong.shopApi.utils.token.JwtUserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopApiApplicationTests {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Test
    void contextLoads() {
        JwtUserInfo userInfo = jwtTokenUtils.getUserInfo("eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ6aGFuZ3NhbiIsInJvbGUiOjAsImV4cCI6MTY2MDIzODA1MH0.HaPHIdLb305OtpVq4gSRJ-kI4DhpJxjvwgmRKbgkb2iJysucRJ-gjSQtf4v-Ax3A6D6rIng51_B2PLLj5r07OsVp-pWO60mh41z41aVY6qtmsYTVmrQJyBmFnIYoEmbCsSCzCf9Jktd8EyI-eOumUKaHpJyDVE4zc7HKnrdquWM");
        System.out.println(userInfo.toString());
    }

}
