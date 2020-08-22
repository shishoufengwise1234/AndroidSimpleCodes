package com.app.simple.codes.manager

/**
 * Created by shishoufeng on 2020/7/18.
 * email:shishoufeng1227@126.com
 *
 *
 * 模拟 内存泄漏
 *
 */
class CallBackManager private constructor() {

    private val mCallBackList = ArrayList<OnCallBackListener>()


    //双重检查锁 的单例
    companion object{
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { CallBackManager() }
    }


    fun addCallBack(onCallBackListener: OnCallBackListener){
        mCallBackList.add(onCallBackListener)
    }

    fun removeCallBack(onCallBackListener: OnCallBackListener){
        mCallBackList.remove(onCallBackListener)
    }





}

interface OnCallBackListener{


    fun doFeature()
}