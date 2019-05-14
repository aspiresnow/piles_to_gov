package com.piles.web.config;

import com.alibaba.fastjson.JSON;
import com.piles.sdk.entity.CommonResponse;
import com.piles.web.exception.PileException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"com.piles.web.controller"})
@Slf4j
public class CommonMessageHandler implements ResponseBodyAdvice<Object> {

    public CommonMessageHandler() {
    }

    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    //用于记录接口返回值
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //注意要try住，不要因为这里出问题而影响正常流程
        try {
            HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
            String url = req.getServletPath();
            log.info("请求url:{} 接口返回值:{}", url, JSON.toJSONString(body));
        } catch (Exception e) {

        }
        return body;
    }

    //拦截 OrderException
    @ExceptionHandler(PileException.class)
    @ResponseBody
    public CommonResponse handleOrderException(HttpServletRequest request, PileException e) {
        try {
            PileException.Level level = e.getLevel();
            String url = request.getServletPath();
            log.info("请求url:{}", url);
            //根据OrderException中的Level来打印不同级别的日志
            switch (level) {
                case WARN://参数化打印
                    log.warn(e.getMessage(), e.getArgs());
                    break;
                case INFO:
                    log.info(e.getMessage(), e.getArgs());
                    break;
                case DEBUG:
                    log.debug(e.getMessage(), e.getArgs());
                    break;
                default:
                    log.error(e.getMessage(), e.getArgs());
            }
        } catch (Exception e1) {
            log.error("全局异常拦截器记录异常时出现问题:{}", e1);
        }
        String msg = StringUtils.trimToEmpty(e.getMsg());
        if (StringUtils.isBlank(msg)) {
            msg = "unknow error";//没有配置返回unknown error
        }
        String code = StringUtils.isBlank(e.getCode()) ? "1" : e.getCode();
        return new CommonResponse<String>(code, msg);
    }

    //拦截 Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse handleException(Exception e) {
        log.error("Unhandled Exception", e);
        return new CommonResponse("500", "Server Exception. Please contact the Administrator.");
    }
}
