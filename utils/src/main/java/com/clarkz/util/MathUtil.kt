package com.clarkz.util

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author: Clark Zhong
 * @date: 2023/08/21
 * @email: 290287498@qq.com
 * @description: 数学计算工具类
 */
object MathUtil {

    /**
     * 四舍五入精确到小数点后几位
     * @param value 待四舍五入的数字
     * @param scale 小数点后几位
     * @return float 四舍五入后的数字
     */
    fun roundHalfUp(value: Double, scale: Int): Float {
        return BigDecimal(value).setScale(scale, RoundingMode.HALF_UP).toFloat()
    }

    /**
     * 四舍五入精确到小数点后几位
     * @param valStr 待四舍五入的数字
     * @param scale 小数点后几位
     * @return float 四舍五入后的数字
     */
    fun roundHalfUp(valStr: String, scale: Int): Float {
        try {
            val value = valStr.toDouble()
            return BigDecimal(value).setScale(scale, RoundingMode.HALF_UP).toFloat()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0.0f
    }

    /**
     * 精确到小数点后几位，多余的直接舍弃
     * @param value 待精确的数字
     * @param scale 小数点后几位
     * @return float 返回的数字
     */
    fun roundDown(value: Double, scale: Int): Float {
        return BigDecimal(value).setScale(scale, RoundingMode.DOWN).toFloat()
    }

    /**
     * 精确到小数点后几位，多余的直接舍弃
     * @param valStr 待精确的数字
     * @param scale 小数点后几位
     * @return float 返回的数字
     */
    fun roundDown(valStr: String, scale: Int): Float {
        try {
            val value = valStr.toDouble()
            return BigDecimal(value).setScale(scale, RoundingMode.DOWN).toFloat()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0.0f
    }
}