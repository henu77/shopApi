package cn.malong.shopApi.handler;

import com.itheima.pinda.common.handler.DefaultGlobalExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author marlone
 * @Date 2022/8/9 2:03
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends DefaultGlobalExceptionHandler {
}
