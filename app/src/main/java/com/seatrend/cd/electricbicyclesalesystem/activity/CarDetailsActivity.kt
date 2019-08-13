package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import kotlinx.android.synthetic.main.activity_car_details.*
import java.lang.Exception

class CarDetailsActivity : BaseActivity() {


    override fun initView() {
        setPageTitle(getString(R.string.details))

        try {
            val dataBean = CarMessageActivity.mCarMessageEntity!!.data
            tv_cjh.text=dataBean.clsbdm
            tv_ddjh.text=dataBean.djh
            tv_sczl.text=dataBean.sczbzl+"kg"
            tv_sccs.text=dataBean.sjss+"km/h"
            tv_lp.text=dataBean.lp+"mm"
        }catch (e:Exception){
            tv_cjh.text="-"
            tv_ddjh.text="-"
            tv_sczl.text="-"+"kg"
            tv_sccs.text="-"+"km/h"
            tv_lp.text="-"+"mm"
            showToast(e.message.toString())
        }

        btn_again.setOnClickListener {
            val intent=Intent(this@CarDetailsActivity,CarMessageActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_car_details
    }
}
