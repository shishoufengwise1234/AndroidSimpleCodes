package com.app.simple.codes.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.simple.codes.R
import com.app.simple.codes.api.GitHubAPIService
import com.app.simple.codes.utils.P
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by shishoufeng on 2020-05-04.
 * email:shishoufeng1227@126.com
 *
 *
 * RxJava 相关测试
 *
 */
class RxJavaActivity : AppCompatActivity(){

    private val TAG = "RxJavaActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
//            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val gitHubAPIService = retrofit.create(GitHubAPIService::class.java)

        var dis : Disposable

        gitHubAPIService.getUserRepos("shishoufengwise1234")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String>{
                override fun onComplete() {
                    P.outI(TAG,"onComplete" )


                }

                override fun onSubscribe(d: Disposable) {

                    dis = d
                }

                override fun onNext(t: String) {

                    P.outI(TAG,"t = $t")

                }

                override fun onError(e: Throwable) {
                    P.outI(TAG,"onError ${e.printStackTrace()}" )

                }
            })

//
//        var dis : Disposable?
//
//        Observable.just("Hello Word")
//            .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<String> {
//                override fun onComplete() {
//                    P.outI(TAG,"onComplete" )
//
//                    if (dis!!.isDisposed){
//
//                    }
//                }
//
//                override fun onSubscribe(d: Disposable?) {
//                    P.outI(TAG,"onSubscribe" )
//
//                    dis = d
//                }
//
//                override fun onNext(t: String?) {
//                    P.outI("t $t")
//
//                    tvRxStatus.text = t
//
//
//                }
//
//                override fun onError(e: Throwable?) {
//                    P.outI(TAG,"onError ${e?.printStackTrace()}" )
//
//                }
//
//            })

    }
}

