package com.app.simple.codes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.simple.codes.R
import com.app.simple.codes.inter.OnItemPairCallBackListener

/**
 * Created by shishoufeng on 2020-05-03.
 * email:shishoufeng1227@126.com
 *
 *
 *
 *
 */
class DashboardListAdapter(context: Context,listPair: List<Pair<Int,String>>) : RecyclerView.Adapter<DashboardListAdapter.DashboardListViewHolder>() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    private var list = listPair

    var onItemPairCallBackListener: OnItemPairCallBackListener<Int,String>? = null
        get() {
            return field
        }
        set(value){
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardListViewHolder {
        return DashboardListViewHolder(layoutInflater.inflate(R.layout.item_dashboard_home_view,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DashboardListViewHolder, position: Int) {
        val pair = list[position]

        holder.tvDashboadText.text = pair.second

        holder.llDashboardGroup.setOnClickListener {

            onItemPairCallBackListener?.onItemClick(pair)

        }
    }


    class DashboardListViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){

        var llDashboardGroup:LinearLayout = itemview.findViewById(R.id.llDashboardGroup)
        var tvDashboadText:TextView = itemview.findViewById(R.id.tvDashboardText)

    }


}