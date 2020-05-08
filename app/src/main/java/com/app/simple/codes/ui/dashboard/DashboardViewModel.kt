package com.app.simple.codes.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.simple.codes.constants.Dashboard

class DashboardViewModel : ViewModel() {

    private val _ListPair = MutableLiveData<List<Pair<Int,String>>>().apply {

        value = listOf(Pair(Dashboard.DASH_TYPE_1,"MyTestView"),
            Pair(Dashboard.DASH_TYPE_2,"RxJava"))
    }


    val listPair: LiveData<List<Pair<Int,String>>> = _ListPair



}