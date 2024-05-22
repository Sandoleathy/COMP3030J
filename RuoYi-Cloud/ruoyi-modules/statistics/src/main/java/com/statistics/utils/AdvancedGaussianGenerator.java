package com.statistics.utils;

import java.util.Random;

public class AdvancedGaussianGenerator {
    private static final Random random = new Random();

    public static float generateAdvancedGaussian(float base, float min, float max, float stdDev) {
        double distanceToMin = base - min;
        double distanceToMax = max - base;

        // 计算动态均值
        float mean = base;
        if (distanceToMin < stdDev) {
            mean += (stdDev - distanceToMin) / 2; // 如果基准值接近下边界，增加均值
        }
        if (distanceToMax < stdDev) {
            mean -= ( stdDev - distanceToMax) / 2; // 如果基准值接近上边界，减少均值
        }

        // 生成高斯随机数
        float result = (float) (mean + random.nextGaussian() * stdDev);

        // 处理超出边界的情况
        if (result < min) {
            result = min + (min - result); // 超出下边界，使用环绕方式调整
        } else if (result > max) {
            result = max - (result - max); // 超出上边界，使用环绕方式调整
        }

        return result;
    }


}

