package com.seatrend.cd.electricbicyclesalesystem.adpater

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.fragment.WarningMessageFragment
import com.seatrend.cd.electricbicyclesalesystem.entity.WarningMessageEntity
import com.seatrend.cd.electricbicyclesalesystem.util.StringUtils

/**
 * Created by seatrend on 2018/12/27.
 */

class WarningMessageAdapter(private val mContext: Context) : RecyclerView.Adapter<WarningMessageAdapter.MyViewHolder>() {


    private var listData= ArrayList<WarningMessageEntity.DataBean>()

    public fun addData(data:ArrayList<WarningMessageEntity.DataBean>){
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_adapter_warning_message, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initItemView(listData[position])

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         var tvXm:TextView?=null
         var tvSfzhm:TextView?=null
         var tvCjrq:TextView?=null
         internal  var tvCxcj:TextView?=null
         var tvCjh:TextView?=null
         var tvWtgyy:TextView?=null

         init {
             tvXm=itemView.findViewById<TextView>(R.id.tv_xm)
             tvSfzhm=itemView.findViewById<TextView>(R.id.tv_sfzhm)
             tvCjrq=itemView.findViewById<TextView>(R.id.tv_cjrq)
             tvCxcj=itemView.findViewById<TextView>(R.id.tv_cxcj)
             tvCjh=itemView.findViewById<TextView>(R.id.tv_cjh)
             tvWtgyy=itemView.findViewById<TextView>(R.id.tv_wtgyy)

             tvCxcj!!.setOnClickListener {


                 (mContext as WarningMessageFragment).queryCarMessage(listData[adapterPosition].zcbm+"")
                 //WarningMessageFragment
             }
         }
          fun initItemView(dataBean:WarningMessageEntity.DataBean){
              tvXm!!.text=dataBean.syr
              tvSfzhm!!.text=StringUtils.StringShowStar(dataBean.sfzmhm,6,6)
              tvCjrq!!.text=StringUtils.longToStringDataNoHour(dataBean.slrq)
              tvCjh!!.text=dataBean.zcbm
              tvWtgyy!!.text=dataBean.ywyy

         }
     }
}
