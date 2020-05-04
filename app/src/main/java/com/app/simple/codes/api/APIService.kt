package com.app.simple.codes.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by shishoufeng on 2020-05-01.
 * email:shishoufeng1227@126.com
 *
 *
 *
 *
 */
interface APIService{


    @Headers("Content-type:Application/json")
    @GET("getuser")
    fun getUser():Call<String>



}