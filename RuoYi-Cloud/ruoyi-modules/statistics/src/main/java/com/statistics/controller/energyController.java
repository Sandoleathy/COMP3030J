package com.statistics.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.redis.service.RedisService;
import com.statistics.dto.CompleteEnergySystemData;
import com.statistics.dto.DataFlow;
import com.statistics.services.EnergySensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/energy")
public class energyController extends BaseController {




    @Autowired
    private EnergySensorDataService energySensorDataService;





    @GetMapping("/test")
    public String test(){
        return "test1234567";
    }


    @GetMapping("/dataFlow")
    public DataFlow generateDataFlow(){
        return energySensorDataService.generateDataFlow(System.currentTimeMillis() / 1000);



    }


}
