package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import kotlinx.android.synthetic.main.activity_register_bicycle_result.*

class RegisterBicycleResultActivity : BaseActivity() {



    override fun initView() {

        setPageTitle(getString(R.string.djjg))
        bindEvent()
    }

    private fun bindEvent(){
        btn_hpbf.setOnClickListener {

        }
        tv_back_home.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
    override fun getLayout(): Int {
        return R.layout.activity_register_bicycle_result
    }


}
