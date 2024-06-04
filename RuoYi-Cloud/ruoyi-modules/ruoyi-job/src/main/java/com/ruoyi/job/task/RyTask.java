package com.ruoyi.job.task;

import org.springframework.stereotype.Component;
import com.ruoyi.common.core.utils.StringUtils;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("Execute multi-parameter method: String type {}, Boolean type {}, long integer type {}, floating point type {}, integer type {}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("Execute parameterized method:" + params);
    }

    public void ryNoParams()
    {
        System.out.println("Execute parameterless method");
    }
}
