package com.ruoyi.gateway.handler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Optional;

import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.utils.ip.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.ruoyi.common.core.exception.CaptchaException;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.gateway.service.ValidateCodeService;
import reactor.core.publisher.Mono;
import com.ruoyi.common.redis.service.RedisService;

import javax.servlet.http.HttpServletRequest;


/**
 * 验证码获取
 *
 * @author ruoyi
 */
@Component
public class ValidateCodeHandler implements HandlerFunction<ServerResponse>
{
    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private RedisService redisService;

    //检验IP是否需要验证码
    public boolean needCode(String ip){
        System.out.println("Determine if need ip: "+ip);
        String cacheKey=CacheConstants.SYS_LOGIN_FAIL_TIME+ip;
        return redisService.hasKey(cacheKey) && (int)redisService.getCacheObject(cacheKey)>=CacheConstants.NEED_CODE_FAIL_TIME-1;
    }


    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest)
    {


        AjaxResult ajax;


        try
        {
            ajax = validateCodeService.createCaptcha(needCode(IpUtils.getIpAddr(serverRequest)));
        }
        catch (CaptchaException | IOException e)
        {
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(ajax));
    }


}
