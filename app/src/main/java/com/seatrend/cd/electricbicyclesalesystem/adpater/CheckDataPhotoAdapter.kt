package com.seatrend.cd.electricbicyclesalesystem.adpater

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.R.id.tv_type
import com.seatrend.cd.electricbicyclesalesystem.activity.ShowPhotoActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.PhotoTypeEntity
import java.util.ArrayList

/**
 * Created by seatrend on 2019/5/15.
 */
class CheckDataPhotoAdapter(private var mContext:Context?=null): RecyclerView.Adapter<CheckDataPhotoAdapter.MyViewHolder>() {


    private var data = ArrayList<PhotoTypeEntity.DataBean.ConfigBean>()


    public fun setPhotoType(list: ArrayList<PhotoTypeEntity.DataBean.ConfigBean>){
        this.data=list
        notifyDataSetChanged()
    }

    public fun setPhoto(position: Int,path: String){
        data[position].zplj=path
        notifyItemChanged(position)
    }

    public fun getDataList():ArrayList<PhotoTypeEntity.DataBean.ConfigBean>{
        return this.data
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
       var view = LayoutInflater.from(mContext).inflate(R.layout.item_check_data_photo_adapter,parent,false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder!!.initItemView(data[position])
    }



    inner class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        private var tvType:TextView?=null
        private var ivPhoto:ImageView?=null
        private var ivDelete:ImageView?=null
        init {
            tvType=itemView.findViewById(R.id.tv_type)
            ivPhoto=itemView.findViewById(R.id.iv_photo)
            ivDelete=itemView.findViewById(R.id.iv_delete)
            ivPhoto!!.setOnClickListener {
               if (mOnClickListener!=null && data[adapterPosition].zplj==null){
                   mOnClickListener!!.itemOnClick(adapterPosition)
               }else if (data[adapterPosition].zplj!=null){
                   val intent=Intent(mContext,ShowPhotoActivity::class.java)
                  intent.putExtra(Constants.PATH, data[adapterPosition].zplj)
                   mContext!!.startActivity(intent)

                   (mContext as BaseActivity).startRotateAlphaAcaleAnimation()
               }
            }
            ivDelete!!.setOnClickListener {
                data[adapterPosition].zplj=null
                notifyItemChanged(adapterPosition)
                mDeleteListener!!.itemdelete(adapterPosition)
            }

        }
         fun initItemView(bean: PhotoTypeEntity.DataBean.ConfigBean){
            tvType!!.text=bean.zmmc
             if (bean.zplj!=null && bean.zplj.isNotEmpty()){
                 Glide.with(mContext).load(bean.zplj).centerCrop().error(R.drawable.error_image).into(ivPhoto)
                 ivDelete!!.visibility=View.VISIBLE
             }else{
                 ivPhoto!!.setImageResource(R.drawable.take_photo)
                 ivPhoto!!.scaleType=ImageView.ScaleType.CENTER
                 ivDelete!!.visibility=View.GONE
             }

        }
    }
    private var mOnClickListener:itemOnClickListener?=null

     fun setItemOnClick(listener:itemOnClickListener){
        mOnClickListener=listener
    }
     interface itemOnClickListener{
        fun itemOnClick(position: Int)
    }

    private var mDeleteListener:itemDeleteClickListener?=null

    fun setItemdeleteClick(listen:itemDeleteClickListener){
        mDeleteListener=listen
    }
     interface  itemDeleteClickListener  {
        fun itemdelete(position: Int)
    }
}