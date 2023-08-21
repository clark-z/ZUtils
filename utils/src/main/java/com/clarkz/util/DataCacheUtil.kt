package com.clarkz.util

import android.content.Context
import android.os.Environment
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author: Clark Zhong
 * @date: 2023/08/21
 * @email: 290287498@qq.com
 * @description: 缓存管理工具类
 */
object DataCacheUtil {

//    private val TAG = DataCacheUtil::class.java.simpleName

    /**
     * 文件存储目录 Context.getExternalFilesDir()
     * SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
     * 缓存文件夹目录 Context.getExternalCacheDir()
     * SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
     */
    fun getTotalCacheSize(context: Context): Long {
        //手机内部缓存
        var cacheSize = getFolderSize(context.cacheDir)

//        Log.d(TAG, "cache dir: ${context.cacheDir}")

        //手机外部缓存
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            Log.d(TAG, "external cache dir: ${context.externalCacheDir}")
            cacheSize += getFolderSize(context.externalCacheDir)
        }

        return cacheSize
    }

    /**
     * 获取缓存大小(后缀单位的字符串)
     */
    fun getTotalCacheShow(context: Context): String {
        val size = getTotalCacheSize(context)
        return getFormatSize(size)
    }

    /**
     * 清除全部缓存
     */
    fun clearAllCache(context: Context) {
        deleteDir(context.cacheDir)
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.externalCacheDir)
        }
    }


    /**
     * 删除指定文件夹
     */
    private fun deleteDir(dir: File?): Boolean {

        if (dir?.isDirectory == true) {
            val children = dir.list()
            for (child in children!!) {
                return deleteDir(File(dir, child))
            }
        }

        return dir?.delete() ?: false
    }

    /**
     * 获取指定文件夹大小
     * @return Long
     */
    private fun getFolderSize(file: File?): Long {
        var size: Long = 0

        try {
            val fileList = file?.listFiles()
            fileList?.forEach {
                size += if (it.isDirectory) {
                    getFolderSize(it)
                } else {
                    it.length()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return size
    }

    /**
     * 格式化文件大小
     * @return String
     */
    private fun getFormatSize(size: Long): String {
        val kiloByte = size / 1024.0
        if (kiloByte < 1) {
            val kiloByteDec = BigDecimal(kiloByte)
            return kiloByteDec.setScale(1, RoundingMode.HALF_UP).toPlainString() + "KB"
        }

        val megaByte = kiloByte / 1024.0
        if (megaByte < 1) {
            val kiloByteDec = BigDecimal(kiloByte)
            return kiloByteDec.setScale(1, RoundingMode.HALF_UP).toPlainString() + "KB"
        }

        val gigaByte = megaByte / 1024.0
        if (gigaByte < 1) {
            val megaByteDec = BigDecimal(megaByte)
            return megaByteDec.setScale(1, RoundingMode.HALF_UP).toPlainString() + "MB"
        }

        val teraByte = gigaByte / 1024.0
        if (teraByte < 1) {
            val gigaByteDec = BigDecimal(gigaByte)
            return gigaByteDec.setScale(1, RoundingMode.HALF_UP).toPlainString() + "GB"
        }

        val teraByteDec = BigDecimal(teraByte)

        return teraByteDec.setScale(1, RoundingMode.HALF_UP).toPlainString() + "TB"
    }
}