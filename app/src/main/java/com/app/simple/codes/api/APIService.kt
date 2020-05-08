package com.app.simple.codes.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

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


interface GitHubAPIService{

    /**
     *
     * 获取 当前用户名下所有项目
     *
     */
    @GET("users/{userName}/repos")
    fun getUserRepos(@Path("userName") userName:String):Observable<String>
    /**
     *
     * 获取 当前用户名下所有项目
     *
     */
    @GET("users/{userName}/repos")
    fun getUserReposs(@Path("userName") userName:String):Single<String>




}