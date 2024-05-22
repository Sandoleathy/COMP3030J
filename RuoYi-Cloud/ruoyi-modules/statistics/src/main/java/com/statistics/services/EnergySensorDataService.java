package com.statistics.services;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.redis.service.RedisService;
import com.statistics.dto.CompleteEnergySystemData;
import com.statistics.dto.DataFlow;
import com.statistics.utils.ProducedEnergy;
import com.statistics.utils.Voltage;
import com.statistics.utils.Wind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import static com.statistics.utils.AdvancedGaussianGenerator.generateAdvancedGaussian;

@Service
public class EnergySensorDataService {

    @Autowired
    RedisService redisService;

    @Autowired
    WeatherService weatherService;



    public static final String energyDataPrefix = "energy:";

    public static final String sensorPostfix= "sensor";

    private final Random random = new Random();
    public CompleteEnergySystemData createEnergySensorData(long timeSeconds) {
        CompleteEnergySystemData data = new CompleteEnergySystemData();
        data.setTimeSeconds(timeSeconds);

        // Initialize wind power system data
        data.setGeneratorSpeed(100.0f + (300.0f - 100.0f) * random.nextFloat());
        data.setGeneratorTorque(10.0f + (100.0f - 10.0f) * random.nextFloat());
        data.setCurrentWind(0.145f + (1.449f - 0.145f) * random.nextFloat());
        data.setWindEnergyConversionEfficiency(0.2f+0.6f*random.nextFloat());

        // Initialize solar power system data
        data.setDeviceTemperature(20.0f + (60.0f - 20.0f) * random.nextFloat());
        data.setCurrentSolar(0.01f + (2.5f - 0.01f) * random.nextFloat());

        // Initialize hydroelectric power system data
        data.setWaterFlow(100.0f + (1500.0f - 100.0f) * random.nextFloat());
        data.setFlowSpeed(3.0f + (20.0f - 3.0f) * random.nextFloat());
        data.setUpperWaterLevel(12.0f + (15.0f - 12.0f) * random.nextFloat());
        data.setLowerWaterLevel(1.0f + (3.0f - 1.0f) * random.nextFloat());
        data.setTurbineSpeed(10.0f + (100.0f - 10.0f) * random.nextFloat());
        data.setCurrentHydro(10.0f + (100.0f - 10.0f) * random.nextFloat());
        setPower(data);

        return data;
    }

    Voltage voltage=new Voltage();

    private void setPower(CompleteEnergySystemData data){
        data.setPowerHydro(calculatePower(data.getCurrentHydro(),voltage.voltageHydro));
        data.setPowerSolar(calculatePower(data.getCurrentSolar(),voltage.voltageSolar));
        data.setPowerWind(calculatePower(data.getCurrentWind(),voltage.voltageWind));
    }

    // Create a modified copy of an existing EnergySensorData
    public CompleteEnergySystemData modifyEnergySensorData(CompleteEnergySystemData original) {
        CompleteEnergySystemData newData = new CompleteEnergySystemData();
        newData.setTimeSeconds(original.getTimeSeconds() + 5);

        // 风力发电系统数据生成
        newData.setGeneratorSpeed((float) generateAdvancedGaussian(original.getGeneratorSpeed(), 100, 300, 30));
        newData.setGeneratorTorque((float) generateAdvancedGaussian(original.getGeneratorTorque(), 10, 100, 5));
        newData.setCurrentWind((float) generateAdvancedGaussian(original.getCurrentWind(), 0.145f, 1.449f, 0.1f));
        newData.setWindEnergyConversionEfficiency(generateAdvancedGaussian(original.getWindEnergyConversionEfficiency(),0.2f,0.8f,0.005f));
        // 太阳能发电系统数据生成
        newData.setDeviceTemperature((float) generateAdvancedGaussian(original.getDeviceTemperature(), 20, 60, 10));
        newData.setCurrentSolar((float) generateAdvancedGaussian(original.getCurrentSolar(), 0.01f, 2.5f, 0.05f));

        // 落差水力发电系统数据生成
        newData.setWaterFlow((float) generateAdvancedGaussian(original.getWaterFlow(), 100, 1500, 300));
        newData.setFlowSpeed((float) generateAdvancedGaussian(original.getFlowSpeed(), 3, 20, 4));
        newData.setUpperWaterLevel((float) generateAdvancedGaussian(original.getUpperWaterLevel(), 12, 15, 1));
        newData.setLowerWaterLevel((float) generateAdvancedGaussian(original.getLowerWaterLevel(), 1, 3, 0.5f));
        newData.setTurbineSpeed((float) generateAdvancedGaussian(original.getTurbineSpeed(), 10, 100, 20));
        newData.setCurrentHydro(generateAdvancedGaussian(original.getCurrentHydro(),10,100,20));
        setPower(newData);
        return newData;
    }


    private void batchCompleteSensorData(List<CompleteEnergySystemData> sensorDataList, Wind wind){
                sensorDataList.forEach(
                        i ->setWindConvertEfficiency(i, wind)
                );
        cacheProducedEnergy(sensorDataList);
    }


    float calculatePower(float i,float v){

        return i*v;
    }





    String cachePrefix="produced_energy";
    int timeUtil=5;//以五秒作为一个单位
    private void setProducedEnergyCache(long timeSecond,long hydro,long solar,long wind){

        redisService.setCacheObject(cachePrefix,new ProducedEnergy(timeSecond,hydro,solar,wind));
    }




    private void setProducedEnergy(CompleteEnergySystemData baseData,CompleteEnergySystemData toSetData,Boolean reverse){
        long hydroEnergy= (long) (baseData.getPowerHydro()+timeUtil*toSetData.getPowerHydro()*(reverse?-1:1));
        long windEnergy= (long) (baseData.getPowerWind()+timeUtil*toSetData.getPowerWind()*(reverse?-1:1));
        long solarEnergy= (long) (baseData.getPowerSolar()+timeUtil*toSetData.getPowerSolar()*(reverse?-1:1));
        if(hydroEnergy<0){
            hydroEnergy=-hydroEnergy;
        }
        if(windEnergy<0){
            windEnergy=-windEnergy;
        }
        if(hydroEnergy<0){
            solarEnergy=-solarEnergy;
        }



        //补全数据
        toSetData.setEnergyProducedSolar(solarEnergy);
        toSetData.setCarbonReductionSolar(carbonReduction(solarEnergy));

        toSetData.setEnergyProducedWind(windEnergy);
        toSetData.setCarbonReductionWind(carbonReduction(windEnergy));

        toSetData.setEnergyProducedHydro(hydroEnergy);
        toSetData.setCarbonReductionHydro(carbonReduction(hydroEnergy));

    }

    private void cacheProducedEnergy(List<CompleteEnergySystemData> list){



        if (!redisService.hasKey(cachePrefix)){
            setProducedEnergyCache(715756400,0,0,0);
        }
        ProducedEnergy originalEnergy=redisService.getCacheObject(cachePrefix);
        long timeNow=originalEnergy.getTimeSecond();
        long timeStart=list.get(0).getTimeSeconds();
        long timeBetween=timeStart-timeNow;
        //有断档的时间的话补全数据
        if(timeBetween>=timeUtil){
            CompleteEnergySystemData randomSensorData=createEnergySensorData(getTimeDividableByFive(0));
            originalEnergy=new ProducedEnergy(timeStart,timeBetween*(long) randomSensorData.getPowerHydro(),timeBetween*(long) randomSensorData.getPowerSolar(),timeBetween*(long) randomSensorData.getPowerWind());//这里单位是kwh
        }

        //先把数据发给跟缓存中的能源数据对应的
        boolean startAdd = false;
        int baseIndex = -1;
        CompleteEnergySystemData lastData = null;
        for(CompleteEnergySystemData data:list){
            if (!startAdd&&originalEnergy.getTimeSecond()==data.getTimeSeconds()){
                startAdd=true;
                baseIndex=list.indexOf(data);
                lastData=data;
            }
            else if (startAdd){
                setProducedEnergy(lastData,data,false);
                lastData=data;
            }
        }



        for (int i=baseIndex;i>0;i--){
            setProducedEnergy(list.get(i),list.get(i-1),true);
        }







        //接下来根据这一时刻的数据更新缓存
        setProducedEnergyCache(lastData.getTimeSeconds(), lastData.getEnergyProducedHydro(),lastData.getEnergyProducedSolar(),lastData.getEnergyProducedWind());



    }

    private long carbonReduction(long energy){
        return (long) (energy*1.1);
    }

    private void setWindConvertEfficiency(CompleteEnergySystemData completeEnergySystemData,Wind wind){
        completeEnergySystemData.setWindSpeed(wind.getWindSpeed());
        completeEnergySystemData.setTheoreticalMaxPower(completeEnergySystemData.getPowerWind()/completeEnergySystemData.getWindEnergyConversionEfficiency());
        completeEnergySystemData.setWindEnergyConversionEfficiency(completeEnergySystemData.getPowerWind()/completeEnergySystemData.getTheoreticalMaxPower());
    }



    private Wind getWindSpeedAndDirection(){
        Wind toReturn=new Wind();
        String json=weatherService.CheckAndGet(WeatherService.NOW_SUFFIX);
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject now = jsonObject.getJSONObject("now");

        String windSpeedKmh = now.getString("windSpeed");

        toReturn.setWindSpeed((float) (Float.parseFloat(windSpeedKmh) / 3.6));

        String windDirection=now.getString("windDir");
        toReturn.setWindDirectionAngle(Float.parseFloat(now.getString("wind360")));
        toReturn.setWindDirection(windDirection);
        return toReturn;

    }

    private float calculateTheoreticalMaxPower(float bladeArea,float airDecency ,float windSpeed,float Cp){
        return (float) (0.5*bladeArea*airDecency*windSpeed*windSpeed*windSpeed*Cp);
    }
    String redisKey=energyDataPrefix+sensorPostfix;

    private long getTimeDividableByFive(long time){
        return (time+4)/ 5 * 5;
    }

    public DataFlow generateDataFlow(long currentTimeSeconds){
        System.out.println(1);
        List<CompleteEnergySystemData> sensorDataList;


        // 定义时间范围：前30分钟
        long thirtyMinutesInSeconds = TimeUnit.MINUTES.toSeconds(5);

        long endTime=getTimeDividableByFive(currentTimeSeconds);

        // 计算30分钟前的时间戳（以秒为单位）
        long startTime = endTime - thirtyMinutesInSeconds;




        if(!redisService.hasKey(redisKey)){
       ;
            sensorDataList=new ArrayList<>();

        }else {
            sensorDataList=redisService.getCacheList(redisKey);
        }

        sensorDataList.removeIf(d -> d.getTimeSeconds() < startTime);

        if(sensorDataList.isEmpty()){
            CompleteEnergySystemData lateData= createEnergySensorData(startTime);
            sensorDataList.add(lateData);
        }



        System.out.println(2);
        for (long i=sensorDataList.get(sensorDataList.size()-1).getTimeSeconds();i<endTime;i+=timeUtil) {
            System.out.println(i);
            CompleteEnergySystemData nextData = modifyEnergySensorData(sensorDataList.get(sensorDataList.size() - 1));
            sensorDataList.add(nextData);

        }





        System.out.println(3);

        Wind wind=getWindSpeedAndDirection();

        batchCompleteSensorData(sensorDataList,wind);
        //将List存入redis缓存
        System.out.println("count: "+redisService.setCacheList(redisKey,sensorDataList));
        redisService.expire(redisKey,300);

        return new DataFlow(sensorDataList,wind,new Voltage());
    }




    public CompleteEnergySystemData realTimeData(){
        List<CompleteEnergySystemData>energySensorData=redisService.getCacheList(redisKey);
        long currentTimeSecond=getTimeDividableByFive(System.currentTimeMillis() / 1000);
        return energySensorData.stream().filter(i -> i.getTimeSeconds() == currentTimeSecond).findFirst().orElseGet(() -> generateDataFlow(System.currentTimeMillis() / 1000 + TimeUnit.MINUTES.toSeconds(5)).getEnergySystemDataFlow().get(0));
    }
}
