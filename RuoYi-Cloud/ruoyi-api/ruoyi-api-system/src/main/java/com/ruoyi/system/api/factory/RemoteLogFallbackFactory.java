package com.ruoyi.system.api.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteLogService;
import com.ruoyi.system.api.domain.SysLogininfor;
import com.ruoyi.system.api.domain.SysOperLog;

/**
 * 日志服务降级处理
 * 
 * @author ruoyi
 */
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteLogFallbackFactory.class);

    @Override
    public RemoteLogService create(Throwable throwable)
    {
        log.error("Log service call failed: {}", throwable.getMessage());
        return new RemoteLogService()
        {
            @Override
            public R<Boolean> saveLog(SysOperLog sysOperLog, String source)
            {
                return R.fail("Failed to save operation log:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> saveLogininfor(SysLogininfor sysLogininfor, String source)
            {
                return R.fail("Failed to save login log:" + throwable.getMessage());
            }
        };

    }
}
