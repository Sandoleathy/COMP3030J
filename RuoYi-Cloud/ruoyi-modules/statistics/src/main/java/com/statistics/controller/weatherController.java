package com.statistics.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.statistics.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/weather")
public class weatherController extends BaseController {

    public static final String RAIN_SUFFIX="/minutely/5m";

    public static final String DISASTER_SUFFIX="/warning/now";



    public static final String DETAIL_SUFFIX="/weather/3d";

    @Autowired
    private WeatherService weatherService;


    @RequiresPermissions("weather:detail")
    @GetMapping("/detail")
    public String getDetail(){
        return weatherService.CheckAndGet(DETAIL_SUFFIX);
    }

    @RequiresPermissions("weather:rain")
    @GetMapping("/rain")
    public String getRain(){
        return weatherService.CheckAndGet(RAIN_SUFFIX);
    }

    @RequiresPermissions("weather:disaster")
    @GetMapping("/disaster")
    public String getDisaster(){
        return weatherService.CheckAndGet(DISASTER_SUFFIX);
    }





}
