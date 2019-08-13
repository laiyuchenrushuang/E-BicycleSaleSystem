package com.seatrend.cd.electricbicyclesalesystem.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CarLoginActivity
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectBicycleActivity
import com.seatrend.cd.electricbicyclesalesystem.activity.MainActivity
import com.seatrend.cd.electricbicyclesalesystem.activity.QrCodeActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.entity.InsuranceDetailsEntity
import com.seatrend.cd.electricbicyclesalesystem.util.BitmapUtils
import com.seatrend.cd.electricbicyclesalesystem.util.OtherUtils
import kotlinx.android.synthetic.main.fragment_dw_wc.*
import kotlinx.android.synthetic.main.fragment_gr_syrxx.*


/**
 * A simple [Fragment] subclass.
 */
class DwWcFragment : BaseFragment() {



    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_dw_wc, container, false)
    }

    override fun initView() {


        bindEvent()
        if ((activity as CollectBicycleActivity).mInsuranceDetailsEntity==null){
            (activity as CollectBicycleActivity).getInsuranceDetails()
        }else{
            setMesageData((activity as CollectBicycleActivity).mInsuranceDetailsEntity!!)
        }

    }

    private fun bindEvent() {

        btn_save_image.setOnClickListener {
          val  path= BitmapUtils.saveBitmap(OtherUtils.getViewBp(m_card_view),System.currentTimeMillis().toString()+".jpg")
            if (!TextUtils.isEmpty(path)){
                showToast(getString(R.string.save_success))
            }

        }
        btn_sacn_qr.setOnClickListener {
            (activity as CollectBicycleActivity).getQrCodeByLsh()

        }
        btn_back_home.setOnClickListener {
            val intent= Intent(activity, MainActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            activity.finish()
        }
    }

    private fun setMesageData(insuranceDetailsEntity: InsuranceDetailsEntity){
        val dataBean = insuranceDetailsEntity.data
        tv_lsh.text=CollectBicycleActivity.mCarMessageEntity!!.data.lsh
        //tv_syr.text=dataBean.syr
        tv_cjh.text=dataBean.zcbm
        tv_ddjh.text=dataBean.ddjh
        tv_pzh.text=dataBean.pzh
        tv_bdh.text=dataBean.bdh
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        setMesageData((activity as CollectBicycleActivity).mInsuranceDetailsEntity!!)

    }

    public fun startActivity(url: String) {
        val intent= Intent(activity, QrCodeActivity::class.java)
        intent.putExtra("url",url)
        startActivity(intent)
    }


}
