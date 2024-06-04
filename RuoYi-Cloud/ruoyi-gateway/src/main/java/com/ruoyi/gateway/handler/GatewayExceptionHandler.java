package com.ruoyi.gateway.handler;

import org.springframework.cloud.gateway.support.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import com.ruoyi.common.core.utils.ServletUtils;
import reactor.core.publisher.Mono;

/**
 * 网关统一异常处理
 *
 * @author ruoyi
 */
@Order(-1)
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GatewayExceptionHandler.class);

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex)
    {
        ServerHttpResponse response = exchange.getResponse();

        if (exchange.getResponse().isCommitted())
        {
            return Mono.error(ex);
        }

        String msg;

        if (ex instanceof NotFoundException)
        {
            msg = "Service not found";
        }
        else if (ex instanceof ResponseStatusException)
        {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            msg = responseStatusException.getMessage();
        }
        else
        {
            msg = "Internal Server Error";
        }

        log.error("[Gateway Exception Handling] Request path: {}, exception information: {}", exchange.getRequest().getPath(), ex.getMessage());

        return ServletUtils.webFluxResponseWriter(response, msg);
    }
}