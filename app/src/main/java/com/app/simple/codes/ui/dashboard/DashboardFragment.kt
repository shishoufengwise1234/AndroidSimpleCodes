package com.app.simple.codes.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.app.simple.codes.R
import com.app.simple.codes.activity.ClassLoaderActivity
import com.app.simple.codes.activity.DashLineActivity
import com.app.simple.codes.activity.MyTestViewActivity
import com.app.simple.codes.activity.RxJavaActivity
import com.app.simple.codes.adapter.DashboardListAdapter
import com.app.simple.codes.constants.Dashboard
import com.app.simple.codes.hotfix.HotFixActivity
import com.app.simple.codes.inter.OnItemPairCallBackListener
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment(), OnItemPairCallBackListener<Int, String> {


    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var dashboardListAdapter: DashboardListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        recycler.layoutManager = GridLayoutManager(activity,2)


        dashboardViewModel.listPair.observe(this, Observer {

            dashboardListAdapter = activity?.let { it1 -> DashboardListAdapter(it1,it) }!!

            dashboardListAdapter.onItemPairCallBackListener = this


            recycler.adapter = dashboardListAdapter

            dashboardListAdapter.notifyDataSetChanged()

        })


    }


    override fun onItemClick(pair: Pair<Int, String>) {

        val intent = Intent()

        when(pair.first){

            // test view
            Dashboard.DASH_TYPE_1->{
                intent.setClass(activity!!,MyTestViewActivity::class.java)
            }
            Dashboard.DASH_TYPE_2->{
               intent.setClass(activity!!,RxJavaActivity::class.java)
            }
            Dashboard.DASH_TYPE_3->{
                intent.setClass(activity!!,ClassLoaderActivity::class.java)
            }
            Dashboard.DASH_TYPE_4->{
                intent.setClass(activity!!,HotFixActivity::class.java)
            }
            Dashboard.DASH_TYPE_5->{
                intent.setClass(activity!!,DashLineActivity::class.java)
            }

        }

        startActivity(intent)
    }
}