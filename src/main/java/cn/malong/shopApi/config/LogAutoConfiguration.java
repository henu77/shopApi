package cn.malong.shopApi.config;

import cn.malong.shopApi.service.LogService;
import com.itheima.pinda.log.event.SysLogListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author marlone
 * @Date 2022/8/9 1:51
 * 日志配置类
 */
@Configuration
public class LogAutoConfiguration {
    //自动配置日志监听器组件
    @Bean
    @ConditionalOnMissingBean
    public SysLogListener sysLogListener(LogService logService){
        return new SysLogListener(optLogDTO -> logService.saveLog(optLogDTO));
    }
}
