package com.statistics.services;

import com.ruoyi.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.TimeUnit;
@Service
public class WeatherService {

    private static final String WEATHER_API_KEY = "76cbdc094c49493b9a1c3f7f6d659ff4";

    private static final Long UPDATE_TIME=5L;


    public static final String WEATHER_API_URL="https://devapi.qweather.com/v7";


    public static final String LOCATION="117.13,40.30";

    public static final String NOW_SUFFIX="/weather/now";

    public static final String REDIS_PREFIX="weather:";


    @Autowired
    private RedisService redisService;


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
}
