package com.statistics.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
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

    private static final String WEATHER_API_KEY = "76cbdc094c49493b9a1c3f7f6d659ff4";

    private static final Long UPDATE_TIME=5L;


    public static final String WEATHER_API_URL="https://devapi.qweather.com/v7";

    public static final String DETAIL_SUFFIX="/weather/3d";
    public static final String LOCATION="117.13,40.30";

    public static final String RAIN_SUFFIX="/minutely/5m";

    public static final String DISASTER_SUFFIX="/warning/now";

    public static final String REDIS_PREFIX="weather:";
    @Autowired
    private RedisService redisService;

    private void updateDetailCache(){

    }

    @Autowired
    private RestTemplate restTemplate;

    public void cacheDetail(String urlSuffix) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(WEATHER_API_URL+urlSuffix)
                .queryParam("location", LOCATION)
                .queryParam("key", WEATHER_API_KEY)
                .queryParam("lang","en");
        String jsonResult = restTemplate.getForObject(builder.toUriString(), String.class);
        redisService.setCacheObject(REDIS_PREFIX+urlSuffix,jsonResult,UPDATE_TIME, TimeUnit.MINUTES);
    }

    public String CheckAndGet(String suffix){
        if(!redisService.hasKey(REDIS_PREFIX+suffix)){
            cacheDetail(suffix);
        }
        return redisService.getCacheObject(REDIS_PREFIX+suffix);
    }


    @RequiresPermissions("weather:detail")
    @GetMapping("/detail")
    public String getDetail(){
        return CheckAndGet(DETAIL_SUFFIX);
    }

    @RequiresPermissions("weather:rain")
    @GetMapping("/rain")
    public String getRain(){
        return CheckAndGet(RAIN_SUFFIX);
    }

    @RequiresPermissions("weather:disaster")
    @GetMapping("/disaster")
    public String getDisaster(){
        return CheckAndGet(DISASTER_SUFFIX);
    }



}
