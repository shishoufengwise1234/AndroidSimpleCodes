package com.app.simple.codes.inter

/**
 * Created by shishoufeng on 2020-05-03.
 * email:shishoufeng1227@126.com
 *
 *
 *
 *
 */

/**
 *
 * 通用 Pair<A, B> 点击事件回调
 */
interface OnItemPairCallBackListener<A, B>{

    fun onItemClick(pair: Pair<A, B>)
}